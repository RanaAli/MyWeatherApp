plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

val apiBaseUrl: String by project
val openweathermapApiKey: String by project

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String", "OPENWEATHERMAP_URL",
            "\"$apiBaseUrl\""
        )
        buildConfigField(
            "String", "OPENWEATHERMAP_API_KEY",
            "\"$openweathermapApiKey\""
        )
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(project(":domain"))
    implementation(Libs.kotlinStdLib)
    implementation(Libs.appCompat)
    implementation(Libs.coreCtx)
    implementation(Libs.material)
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    implementation(Libs.moshi)
    kapt(Libs.moshiCodeGen)

    implementation(Libs.retrofit)

    implementation(Libs.hilt)
    implementation(Libs.hiltJetpack)
    kapt(Libs.hiltCompiler)
    kapt(Libs.hiltJetpackCompiler)

    testImplementation(Libs.junit)
    androidTestImplementation(Libs.testRunner)
    androidTestImplementation(Libs.espresso)
}