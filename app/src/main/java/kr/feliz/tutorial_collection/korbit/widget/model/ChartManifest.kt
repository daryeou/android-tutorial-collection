package kr.feliz.tutorial_collection.korbit.widget.model

import com.google.gson.annotations.SerializedName

data class ChartManifest(
    @SerializedName("base")
    val base: String,
    @SerializedName("counter")
    val counter: String,
    @SerializedName("head")
    val head: String,
    @SerializedName("interval")
    val interval: String,
    @SerializedName("tail")
    val tail: String
)