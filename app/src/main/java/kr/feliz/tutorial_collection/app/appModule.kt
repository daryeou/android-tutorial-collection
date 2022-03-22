package kr.feliz.tutorial_collection.app

import kr.feliz.tutorial_collection.lemonfox.widget.net.ChartApiService
import kr.feliz.tutorial_collection.lemonfox.widget.net.ChartDataSource
import kr.feliz.tutorial_collection.lemonfox.widget.net.RetrofitClient
import kr.feliz.tutorial_collection.sence.koin.Factory
import kr.feliz.tutorial_collection.sence.koin.Student
import kr.feliz.tutorial_collection.sence.koin.Teacher
import org.koin.core.qualifier.named
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

    /* Koin 예제 */
    single(named("cho")) { Teacher("teacher-cho") }

    single(named("tan")) { Teacher("teacher_tan") }

    single { Student("student-kim") }

    single(named("sec")) { Teacher("teacher_tantan", get(named("sec"))) }

    single(named("sec")) { Student("student_kin") }

    single { Factory("Factory:") }

    single(named("factory")) { Teacher("teacher_lin", get(named("sec"))) }

    //TODO viewmodel로 의존성 넘겨줄 것 Trade2ViewModel Trade2Activity 참고

}
