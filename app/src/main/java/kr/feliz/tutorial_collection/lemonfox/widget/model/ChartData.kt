package kr.feliz.tutorial_collection.lemonfox.widget.model

import com.google.gson.annotations.SerializedName

data class ChartData(
    @SerializedName("prev")
    val prev: String?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("t")
    val time: List<Long>,
    @SerializedName("o")
    val open: List<Number>,
    @SerializedName("h")
    val high: List<Number>,
    @SerializedName("l")
    val low: List<Number>,
    @SerializedName("c")
    val close: List<Number>,
    @SerializedName("v")
    val volume: List<Number>
)