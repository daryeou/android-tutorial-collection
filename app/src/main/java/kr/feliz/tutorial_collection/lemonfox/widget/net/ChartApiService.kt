package kr.feliz.tutorial_collection.lemonfox.widget.net

import kr.feliz.tutorial_collection.lemonfox.widget.model.ChartData
import kr.feliz.tutorial_collection.lemonfox.widget.model.ChartManifest
import retrofit2.http.GET
import retrofit2.http.Path

interface ChartApiService {
    @GET("{sequence}")
    suspend fun getManifestData(@Path("sequence") sequence: String): ChartManifest?

    @GET("{sequence}")
    suspend fun getRemoteChartData(@Path("sequence") sequence: String): ChartData
}