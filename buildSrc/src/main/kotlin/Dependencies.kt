import org.gradle.api.Action
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

object Dependencies {
    const val ANDROID_APP_COMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APP_COMPAT}"
    const val ANDROID_CORE_KTX = "androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
    const val OKHTTP3_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_INTERCEPTOR}"
    const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
    const val RETROFIT2_ADAPTER_RXJAVA = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT2}"
    const val RETROFIT2_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT2}"
    const val RETROFIT2_CONVERTER_SCALARS = "com.squareup.retrofit2:converter-scalars:${Versions.RETROFIT2}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
}

fun DependencyHandler.appCompat() {
    implementation(Dependencies.ANDROID_APP_COMPAT)
    implementation(Dependencies.ANDROID_CORE_KTX)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.OKHTTP3_LOGGING_INTERCEPTOR)
    implementation(Dependencies.RETROFIT2)
    implementation(Dependencies.RETROFIT2_ADAPTER_RXJAVA)
    implementation(Dependencies.RETROFIT2_CONVERTER_GSON)
    implementation(Dependencies.RETROFIT2_CONVERTER_SCALARS)
}

fun DependencyHandler.koin() {
    implementation(Dependencies.KOIN_ANDROID)
}

private fun DependencyHandler.implementation(name: String): Dependency? {
    return add("implementation", name)
}

fun DependencyHandler.implementation(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
): ExternalModuleDependency = addDependencyTo(
    this, "implementation", dependencyNotation, dependencyConfiguration
)

private fun DependencyHandler.kapt(name: String) {
    add("kapt", name)
}

private fun DependencyHandler.api(name: String) {
    add("api", name)
}

private fun DependencyHandler.testImplementation(name: String) {
    add("testImplementation", name)
}

private fun DependencyHandler.androidTestImplementation(name: String) {
    add("androidTestImplementation", name)
}

private fun DependencyHandler.debugImplementation(name: String) {
    add("debugImplementation", name)
}
