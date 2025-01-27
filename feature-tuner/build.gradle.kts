import com.loveandlaw.conventionplugins.base.extensions.implementation

plugins {
    id("base.feature.dependencies")
    id("base.feature.config")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "ru.muztache.feature.tuner"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.11.0"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(libs.datastore.preferences)
    implementation(libs.datastore)
    implementation(libs.protobuf.javalite)

    implementation(project(":tarsos-dsp"))
}