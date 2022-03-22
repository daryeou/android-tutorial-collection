package kr.feliz.tutorial_collection.lemonfox.widget.data.model

import kr.feliz.tutorial_collection.lemonfox.widget.model.ChartData
import kr.feliz.tutorial_collection.lemonfox.widget.utils.ChartInterval
import kr.feliz.tutorial_collection.lemonfox.widget.utils.ChartPeriod

data class ChartCacheData(
    val base: String,
    val counter: String,
    val period: ChartPeriod,
    val interval: ChartInterval,
    val data: ChartData,
    val createdAt: Long
)