plugins {
    id("base.common.dependencies")
    id("base.common.config")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ru.muztache.core.data"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:core-common"))
    implementation(project(":core:core-util"))
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.authentication)
}