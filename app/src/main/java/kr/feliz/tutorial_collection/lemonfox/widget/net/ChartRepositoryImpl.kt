package kr.feliz.tutorial_collection.lemonfox.widget.net

import kr.feliz.tutorial_collection.lemonfox.widget.data.model.ChartCacheData
import kr.feliz.tutorial_collection.lemonfox.widget.model.ChartData
import kr.feliz.tutorial_collection.lemonfox.widget.model.GetAssetChartResponse
import kr.feliz.tutorial_collection.lemonfox.widget.utils.ChartInterval
import kr.feliz.tutorial_collection.lemonfox.widget.utils.ChartPeriod
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit


class ChartRepositoryImpl(private val chartDataSource: ChartDataSource): ChartRepository {

    override fun setCachedChartData(data: ChartCacheData) {
        ChartCacheStore.setLastChartData(data)
    }

    override fun getCachedChartData(base: String, counter: String, period: ChartPeriod, interval: ChartInterval): ChartCacheData? {
        return ChartCacheStore.getLastChartData(base, counter, period, interval)
    }

    override suspend fun getRemoteChartData(base: String, counter: String, period: ChartPeriod, interval: ChartInterval): ApiResult<ChartData> {
        val date = LocalDateTime.now()
        val response = chartDataSource.getChartData(base, counter, period, interval, date)
        if (response.status == ApiResult.Status.SUCCESS) {
            response.responseData?.let {
                val data = ChartCacheData(
                    base = base,
                    counter = counter,
                    period = period,
                    interval = interval,
                    data = it,
                    createdAt = System.currentTimeMillis()
                )
                setCachedChartData(data)
            }
        }
        return response
    }

    override suspend fun getChartData(base: String, counter: String, period: ChartPeriod, interval: ChartInterval): ApiResult<GetAssetChartResponse> {
        val lastChartData = getCachedChartData(base, counter, period, interval)
        return if (lastChartData == null) {
            val data = getRemoteChartData(base, counter, period, interval).responseData
            ApiResult.success(GetAssetChartResponse(data?.time ?: emptyList(), data?.close ?: emptyList(), data?.volume ?: emptyList()))
        } else {
            val data = if (isOldCached(lastChartData, period)) {
                getRemoteChartData(base, counter, period, interval)
            } else {
                ApiResult.success(lastChartData.data)
            }.responseData
            ApiResult.success(GetAssetChartResponse(data?.time ?: emptyList(), data?.close ?: emptyList(), data?.volume ?: emptyList()))
        }
    }

    private fun isOldCached(lastChartData: ChartCacheData, period: ChartPeriod): Boolean {
        val now = LocalDateTime.now()
        val cachedAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastChartData.createdAt), ZoneId.systemDefault())
        return when (period) {
            ChartPeriod.PERIOD_ONE_DAY -> cachedAt.until(now, ChronoUnit.MINUTES) >= ChartInterval.INTERVAL_FIVE_MIN.toInt()
            ChartPeriod.PERIOD_ONE_WEEK -> cachedAt.until(now, ChronoUnit.MINUTES) >= ChartInterval.INTERVAL_TEM_MIN.toInt()
            ChartPeriod.PERIOD_ONE_MONTH -> cachedAt.until(now, ChronoUnit.HOURS) >= ChartInterval.INTERVAL_ONE_HOUR.toInt()
            ChartPeriod.PERIOD_THREE_MONTH -> cachedAt.until(now, ChronoUnit.HOURS) >= ChartInterval.INTERVAL_ONE_HOUR.toInt()
            ChartPeriod.PERIOD_ONE_YEAR -> cachedAt.until(now, ChronoUnit.DAYS) >= ChartInterval.INTERVAL_ONE_DAY.toInt()
            ChartPeriod.PERIOD_101_HOUR -> cachedAt.until(now, ChronoUnit.HOURS) >= ChartInterval.INTERVAL_ONE_HOUR.toInt()
            ChartPeriod.PERIOD_101_DAY -> cachedAt.until(now, ChronoUnit.DAYS) >= ChartInterval.INTERVAL_ONE_DAY.toInt()
            ChartPeriod.NONE -> true
        }
    }
}