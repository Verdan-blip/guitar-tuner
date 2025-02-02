import java.net.URI

pluginManagement {
    includeBuild("convention-plugins/base")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "TarsosDSP repository"
            url = URI.create("https://mvn.0110.be/releases")
        }
    }
}

rootProject.name = "guitar-tuner"

include(":app")
include(":core-common")
include(":core-data")
include(":core-theme")
include(":core-util")

include(":feature-tuner:feature-tuner-impl")
include(":feature-tuner:feature-tuner-api")
include(":feature-splash")
include(":feature-tuner")
include(":tarsos-dsp")
