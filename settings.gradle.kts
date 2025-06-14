pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "8.1.0" apply false
        id("org.jetbrains.kotlin.android") version "2.0.21" apply false
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "cv_aziz"
include(":app")
