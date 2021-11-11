package kr.feliz.tutorial_collection.korbit.widget.net
import kr.feliz.tutorial_collection.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    val chart: Retrofit
    init {
        chart = Retrofit.Builder()
            .baseUrl(BuildConfig.CHART_API_SERVER_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }
}