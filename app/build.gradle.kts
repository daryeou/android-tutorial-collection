import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    // compileSdkVersion(rootProject.extra["compileSdkVersion"])
    val compileSdkVersion: Int by rootProject.extra
    compileSdkVersion(compileSdkVersion)

    defaultConfig {
        applicationId = Versions.APPLICATION_ID
        minSdkVersion(Versions.MIN_ANDROID_SDK)
        targetSdkVersion(Versions.TARGET_ANDROID_SDK)
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME
        setProperty("archivesBaseName","${getDateTime()}-$applicationId-$versionName-$versionCode")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    signingConfigs {
//        getByName("debug") {
//            val properties = java.util.Properties()
//            val propsFile = File(rootDir, "keystore.properties")
//            properties.load(propsFile.inputStream())
//            storeFile = File(propsFile.parentFile, properties["storeFile"] as String)
//            keyAlias = properties["keyAlias"] as String
//            keyPassword = properties["keyPassword"] as String
//            storePassword = properties["storePassword"] as String
//        }
//        create("release") {
//            val properties = java.util.Properties()
//            val propsFile = File(rootDir, "release.keystore.properties")
//            properties.load(propsFile.inputStream())
//            storeFile = File(propsFile.parentFile, properties["storeFile"] as String)
//            keyAlias = properties["keyAlias"] as String
//            keyPassword = properties["keyPassword"] as String
//            storePassword = properties["storePassword"] as String
//        }
//    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false // 사용하지 않는 코드 제거 및 프로가드 사용 유무, 빌드시간 증
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            // signingConfig = signingConfigs.getByName("release")
            // buildConfigField("String", "FIREBASE_VERSION_CHECK", BuildConfig.DEV_OPS.FIREBASE_VERSION_CHECK)
            // resValue("string", "build_env", BuildConfig.PROD.APP_NAME)
            isCrunchPngs = false // Png 압축 사용
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            // signingConfig = signingConfigs.getByName("release")
            // buildConfigField("String", "FIREBASE_VERSION_CHECK", BuildConfig.DEV_OPS.FIREBASE_VERSION_CHECK)
            // resValue("string", "build_env", BuildConfig.PROD.APP_NAME)
            isCrunchPngs = false // Png 압축 사용
            isDebuggable = false
        }
    }

    flavorDimensions("api", "mode")
    productFlavors {
        create("dev") {
            dimension = "mode"
            applicationIdSuffix = ".dev" // 어플리케이션 아이디의 접미사
            versionNameSuffix = "-DEV" // 어플리케이션 이름의 접미사
            versionCode = 1
            versionName = "1.0.0"
            buildConfigField("String", "CHART_API_SERVER_BASE_URL", BuildConfig.KORBIT.PROD.CHART_API_SERVER_BASE_URL)
            // resValue("string", "build_env", BuildConfig.PROD.APP_NAME)
        }

        create("full") {
            dimension = "mode"
            applicationIdSuffix = ".release" // 어플리케이션 아이디의 접미사
            versionNameSuffix = "-release" // 어플리케이션 이름의 접미사
            versionCode = 1
            versionName = "1.0.0"
        }

        create("minApi24") {
            dimension = "api"
            minSdk = 24
            versionCode = 30000 + android.defaultConfig.versionCode!!
            versionNameSuffix = "-minApi24"
        }

        create("minApi23") {
            dimension = "api"
            minSdk = 23
            versionCode = 20000  + android.defaultConfig.versionCode!!
            versionNameSuffix = "-minApi23"
        }

        create("minApi21") {
            dimension = "api"
            minSdk = 21
            versionCode = 10000  + android.defaultConfig.versionCode!!
            versionNameSuffix = "-minApi21"
        }
    }

    variantFilter {
        if (name.contains("full",true) || buildType.name.equals("release", true)) {
            ignore = true
        }
    }

//    androidComponents {
//        beforeVariants { variantBuilder ->
//            // To check for a certain build type, use variantBuilder.buildType == "<buildType>"
//            //if (variantBuilder.productFlavors.containsAll(listOf("api" to "minApi21", "mode" to "demo"))) {
//            if (variantBuilder.name.contains("full",true)) {
//                // Gradle ignores any variants that satisfy the conditions above.
//                variantBuilder.enabled = false
//            }
//        }
//    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true // https://prod.velog.io/@9geunu/aar-desugaring
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    packagingOptions {
        jniLibs.keepDebugSymbols.add("**/*.so") // 네이티브 라이브러리 Strip방지기능 : https://devanix.tistory.com/194
    }


}

val lottieVersion = "4.2.0"
val rxjavaVersion = "2.4.0"

dependencies {
    //implementation(project(":libs"))
    //implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    appCompat()
    koin()
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("com.github.gayanvoice:android-animations-kotlin:1.0.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.airbnb.android:lottie:$lottieVersion")
    implementation("androidx.lifecycle:lifecycle-runtime:2.3.1" )
    implementation("androidx.lifecycle:lifecycle-common-java8:2.3.1" )
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1" )
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("io.reactivex.rxjava2:rxkotlin:$rxjavaVersion")
    implementation("androidx.fragment:fragment-ktx:1.3.1")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.google.android.gms:play-services-ads:20.4.0")
    // 뷰페이저 및 인디케이
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.tbuonomo:dotsindicator:4.2")
    // 메테리얼
    implementation("com.google.android.material:material:1.5.0-alpha05")
    // 차트
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    // 레트로핏
    retrofit()
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.0.9")
}

// apk이름을 타임스탬프 반영 : https://marlboroyw.tistory.com/496
fun getDateTime(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("YYYYMMDDHHmmss")
    return current.format(formatter)
}