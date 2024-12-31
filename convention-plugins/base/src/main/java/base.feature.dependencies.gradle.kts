import com.loveandlaw.conventionplugins.base.extensions.androidConfig
import com.loveandlaw.conventionplugins.base.extensions.implementation
import com.loveandlaw.conventionplugins.base.extensions.testImplementation
import com.loveandlaw.conventionplugins.base.extensions.androidTestImplementation
import com.loveandlaw.conventionplugins.base.extensions.debugImplementation
import com.loveandlaw.conventionplugins.base.extensions.libs
import com.loveandlaw.conventionplugins.base.extensions.projectImplementation
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.android")
}

androidConfig {
    buildFeatures {
        compose = true
    }
}

dependencies {

    //Common modules
    projectImplementation(project(":core-common"))
    projectImplementation(project(":core-data"))
    projectImplementation(project(":core-data"))
    projectImplementation(project(":core-theme"))
    projectImplementation(project(":core-util"))

    //Compose
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