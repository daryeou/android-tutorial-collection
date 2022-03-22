package kr.feliz.tutorial_collection.lemonfox.widget.net

import kr.feliz.tutorial_collection.lemonfox.widget.model.ChartData
import kr.feliz.tutorial_collection.lemonfox.widget.utils.ChartInterval
import kr.feliz.tutorial_collection.lemonfox.widget.utils.ChartPeriod
import kr.feliz.tutorial_collection.lemonfox.widget.utils.toEpochSecond
import kr.feliz.tutorial_collection.lemonfox.widget.utils.toUpper
import java.time.LocalDateTime

class ChartDataSource(private val apiService: ChartApiService) {

    suspend fun getChartData(
        base: String,
        counter: String,
        period: ChartPeriod,
        interval: ChartInterval,
        date: LocalDateTime
    ): ApiResult<ChartData> {
        return try {
            var manifestSequence = getManifestSequence(base, counter, interval)
            var manifest = apiService.getManifestData(manifestSequence)

            if (manifest == null) {
                manifestSequence = getManifestSequence(base, counter, null)
                manifest = apiService.getManifestData(manifestSequence)
            }

            val sequence = checkNotNull(manifest).tail
            var data = apiService.getRemoteChartData(sequence)
            val limitTime = getLimitTime(period, date)

            while(true) {
                val firstTime = data.time.firstOrNull()?.toLong()
                if (firstTime == null || firstTime <= limitTime || data.prev.isNullOrEmpty()) {
                    break
                }
                val prevData = apiService.getRemoteChartData(data.prev!!)
                data = appendData(data, prevData)
            }

            data = getFilteredData(data, limitTime)
            ApiResult.success(data)
        } catch (e: Exception) {
            ApiResult.error(ApiError(ApiErrorCase.UNKNOWN_ERROR, e.message))
        }
    }

    private fun getManifestSequence(
        base: String,
        counter: String,
        interval: ChartInterval?
    ): String {
        val intervalValue = interval?.value ?: "1m"
        return "${base.toUpper()}_${counter.toUpper()}_$intervalValue.manifest"
    }

    private fun appendData(data: ChartData, prevData: ChartData): ChartData {
        return ChartData(
            prev = prevData.prev,
            next = data.next,
            time = prevData.time.plus(data.time),
            open = prevData.open.plus(data.open),
            high = prevData.high.plus(data.high),
            low = prevData.low.plus(data.low),
            close = prevData.close.plus(data.close),
            volume = prevData.volume.plus(data.volume)
        )
    }

    private fun getFilteredData(data: ChartData, limitTime: Long): ChartData {
        val time = mutableListOf<Long>()
        val open = mutableListOf<Number>()
        val high = mutableListOf<Number>()
        val low = mutableListOf<Number>()
        val close = mutableListOf<Number>()
        val volume = mutableListOf<Number>()

        data.time.forEachIndexed { index, timestamp ->
            if (timestamp >= limitTime) {
                time.add(data.time[index])
                open.add(data.open[index])
                high.add(data.high[index])
                low.add(data.low[index])
                close.add(data.close[index])
                volume.add(data.volume[index])
            }
        }

        return ChartData(
            prev = data.prev,
            next = data.next,
            time = time,
            open = open,
            high = high,
            low = low,
            close = close,
            volume = volume
        )
    }

    private fun getLimitTime(period: ChartPeriod, date: LocalDateTime): Long {
        val timestamp = date.toEpochSecond()
        val startTime = when (period) {
            ChartPeriod.PERIOD_ONE_DAY -> timestamp - (timestamp % (60 * 5))
            ChartPeriod.PERIOD_ONE_WEEK -> timestamp - (timestamp % (60 * 10))
            ChartPeriod.PERIOD_ONE_MONTH,
            ChartPeriod.PERIOD_THREE_MONTH -> timestamp - (timestamp % (60 * 60))
            ChartPeriod.PERIOD_ONE_YEAR -> date.toLocalDate().toEpochSecond()
            ChartPeriod.PERIOD_101_HOUR -> date.toLocalDate().toEpochSecond()
            ChartPeriod.PERIOD_101_DAY -> date.toLocalDate().toEpochSecond()
            ChartPeriod.NONE -> timestamp
        }
        return startTime - period.value
    }
}