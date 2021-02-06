plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(Libs.kotlinStdLib)

    implementation(Libs.coroutinesCore)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)
}