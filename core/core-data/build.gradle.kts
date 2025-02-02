plugins {
    id("base.common.dependencies")
    id("base.common.config")
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
}