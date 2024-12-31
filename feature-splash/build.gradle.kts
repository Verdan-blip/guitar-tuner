plugins {
    id("base.feature.dependencies")
    id("base.feature.config")
}

android {
    namespace = "com.example.feature.splash"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

}