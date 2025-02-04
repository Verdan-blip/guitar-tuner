plugins {
    id("base.feature.dependencies")
    id("base.feature.config")
}

android {
    namespace = "ru.muztache.feature.signin"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

}