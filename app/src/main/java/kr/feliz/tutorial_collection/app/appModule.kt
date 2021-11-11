package kr.feliz.tutorial_collection.app

import kr.feliz.tutorial_collection.app.preference.PreferenceManagerImpl
import kr.feliz.tutorial_collection.korbit.widget.net.*
import org.koin.dsl.module

val dataModule = module {

    single {
        RetrofitClient
    }

    single {
        get<RetrofitClient>().chart.create(ChartApiService::class.java)
    }

    single {
        ChartDataSource(get())
    }

    //TODO viewmodel로 의존성 넘겨줄 것 Trade2ViewModel Trade2Activity 참고

}
