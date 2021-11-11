package kr.feliz.tutorial_collection.korbit.widget.net

import kr.feliz.tutorial_collection.korbit.widget.data.model.ChartCacheData
import kr.feliz.tutorial_collection.korbit.widget.model.ChartData
import kr.feliz.tutorial_collection.korbit.widget.model.GetAssetChartResponse
import kr.feliz.tutorial_collection.korbit.widget.utils.ChartInterval
import kr.feliz.tutorial_collection.korbit.widget.utils.ChartPeriod

interface ChartRepository {
    fun setCachedChartData(data: ChartCacheData)
    fun getCachedChartData(base: String, counter: String, period: ChartPeriod, interval: ChartInterval): ChartCacheData?
    suspend fun getRemoteChartData(base: String, counter: String, period: ChartPeriod, interval: ChartInterval): ApiResult<ChartData>
    suspend fun getChartData(base: String, counter: String, period: ChartPeriod, interval: ChartInterval): ApiResult<GetAssetChartResponse>
}