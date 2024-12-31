import com.loveandlaw.conventionplugins.base.extensions.androidConfig
import com.loveandlaw.conventionplugins.base.extensions.kotlinJvmCompilerOptions
import com.loveandlaw.conventionplugins.base.extensions.libs
import com.loveandlaw.conventionplugins.base.extensions.projectJavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

androidConfig {

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(projectJavaVersion)
        targetCompatibility = JavaVersion.toVersion(projectJavaVersion)
    }
}

kotlinJvmCompilerOptions {
    jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
}