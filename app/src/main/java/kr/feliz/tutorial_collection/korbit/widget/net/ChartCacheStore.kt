package kr.feliz.tutorial_collection.korbit.widget.net

import android.util.Log
import kr.feliz.tutorial_collection.korbit.widget.data.model.ChartCacheData
import kr.feliz.tutorial_collection.korbit.widget.utils.ChartInterval
import kr.feliz.tutorial_collection.korbit.widget.utils.ChartPeriod
import java.util.concurrent.ConcurrentHashMap

object ChartCacheStore {

    private const val CHART_CACHE_PREFIX = "CHART_CACHE"
    private val dataMap = ConcurrentHashMap<String, ChartCacheData>()

    private fun createKey(base: String, counter: String, period: ChartPeriod, interval: ChartInterval): String {
        return "${CHART_CACHE_PREFIX}_${base}_${counter}_${period}_${interval}"
    }

    fun setLastChartData(data: ChartCacheData) {
        try {
            val key = createKey(data.base, data.counter, data.period, data.interval)
            dataMap[key] = data
        } catch (e: Exception) {
            Log.e(null, null, e)
        }
    }

    fun getLastChartData(base: String, counter: String, period: ChartPeriod, interval: ChartInterval): ChartCacheData? {
        return try {
            val key = createKey(base, counter, period, interval)
            dataMap[key]
        } catch (e: Exception) {
            null
        }
    }

    fun clear() {
        dataMap.clear()
    }
}