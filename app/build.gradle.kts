plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
}

val apiBaseUrl: String by project
val openweathermapApiKey: String by project
val openweathermapApiKeyDebug: String by project

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.rana.myweatherapp"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField(
            "String", "OPENWEATHERMAP_URL",
            "\"$apiBaseUrl\""
        )

        useLibrary("android.test.runner")
        useLibrary("android.test.base")
        useLibrary("android.test.mock")
    }
    buildTypes {

        getByName("debug") {
            buildConfigField(
                "String", "OPENWEATHERMAP_API_KEY",
                "\"$openweathermapApiKeyDebug\""
            )
            
            isMinifyEnabled = false
            debuggable(true)
            applicationIdSuffix = ".debug"
        }

        getByName("release") {
            buildConfigField(
                "String", "OPENWEATHERMAP_API_KEY",
                "\"$openweathermapApiKey\""
            )

            isMinifyEnabled = true
            debuggable(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }



    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(project(":data"))
    implementation(Libs.kotlinStdLib)
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.viewpager2)

    implementation(Libs.coreCtx)

    implementation(Libs.viewModelKtx)
    implementation(Libs.lifecycleExt)
    implementation(Libs.lifecycleKtx)
    kapt(Libs.lifecycleCompiler)

    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)

    implementation(Libs.material)
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    implementation(Libs.hilt)
    implementation(Libs.hiltJetpack)
    kapt(Libs.hiltCompiler)
    kapt(Libs.hiltJetpackCompiler)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitLogInterceptor)

    implementation("com.google.android.gms:play-services-location:17.1.0")

    implementation(Libs.glide)
    kapt(Libs.glideCompiler)

    testImplementation(Libs.coroutinesCore)
    testImplementation(Libs.mockito)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.coreTesting)
    testImplementation(Libs.junit)
    testImplementation(Libs.truth)
    testImplementation(Libs.coroutinesTest)
    androidTestImplementation(Libs.testRunner)
    androidTestImplementation(Libs.espresso)
}
