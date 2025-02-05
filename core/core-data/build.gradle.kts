plugins {
    id("base.common.dependencies")
    id("base.common.config")
    alias(libs.plugins.sqldelight)
}

android {
    namespace = "ru.muztache.core.data"

    sqldelight {
        databases {
            create("guitar_tuner_database") {
                packageName.set("ru.muztache.core.data")
            }
        }
    }

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
    implementation(libs.sqldelight)
}