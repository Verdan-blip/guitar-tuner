import ru.muztache.conventionplugins.base.extensions.implementation

plugins {
    id("base.feature.dependencies")
    id("base.feature.config")
    kotlin("plugin.serialization")
}

android {
    namespace = "ru.muztache.feature.chords"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":feature:feature-chords:feature-chords-api"))
    implementation(libs.kotlin.serialization.json)
}
