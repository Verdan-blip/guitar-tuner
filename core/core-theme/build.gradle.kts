import ru.muztache.conventionplugins.base.extensions.implementation

plugins {
    id("base.common.dependencies")
    id("base.common.config")
}

android {
    namespace = "ru.muztache.core.theme"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:core-common"))
    implementation(project(":core:core-util"))
    implementation(libs.androidx.animation.graphics.android)
}