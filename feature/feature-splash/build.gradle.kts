import ru.muztache.conventionplugins.base.extensions.implementation

plugins {
    id("base.feature.dependencies")
    id("base.feature.config")
}

android {
    namespace = "ru.muztache.feature.splash"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":feature:feature-tuner:feature-tuner-api"))
    implementation(project(":feature:feature-chords:feature-chords-api"))
    implementation(libs.androidx.core.splashscreen)
}