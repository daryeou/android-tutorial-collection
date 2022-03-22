package kr.feliz.tutorial_collection.lemonfox.widget.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetAssetChartResponse(
    @SerializedName("t")
    @Expose
    val t: List<Number>,

    @SerializedName("c")
    @Expose
    val c: List<Number>,

    @SerializedName("v")
    @Expose
    val v: List<Number>
) {
    fun isInvalidData(): Boolean {
        return try {
            val dateList = t
            val priceList = c
            return dateList.isEmpty() ||
                    priceList.isEmpty() ||
                    dateList.size == 1 ||
                    priceList.size == 1 ||
                    dateList.size != priceList.size
        } catch (e: Exception) {
            true
        }
    }
}