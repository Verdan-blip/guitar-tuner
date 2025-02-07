import org.gradle.kotlin.dsl.dependencies
import ru.muztache.conventionplugins.base.extensions.androidConfig
import ru.muztache.conventionplugins.base.extensions.androidTestImplementation
import ru.muztache.conventionplugins.base.extensions.debugImplementation
import ru.muztache.conventionplugins.base.extensions.implementation
import ru.muztache.conventionplugins.base.extensions.libs
import ru.muztache.conventionplugins.base.extensions.testImplementation

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.android")
    id("io.gitlab.arturbosch.detekt")
}

androidConfig {
    buildFeatures {
        compose = true
    }
}

detekt {
    config.from(files("${rootDir}/config/detekt/detekt.yml"))
}

dependencies {

    //Compose
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //DI
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
