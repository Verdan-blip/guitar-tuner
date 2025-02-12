import java.net.URI

include(":core:core-data-impl")


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
include(":core:core-common")
include(":core:core-data-api")
include(":core:core-theme")
include(":core:core-util")

include(":feature:feature-tuner:feature-tuner-impl")
include(":feature:feature-tuner:feature-tuner-api")
include(":feature:feature-chords:feature-chords-api")
include(":feature:feature-chords:feature-chords-impl")
include(":feature:feature-profile")
include(":feature:feature-signin")
include(":feature:feature-signup")
include(":feature:feature-splash")
include(":tarsos-dsp")
