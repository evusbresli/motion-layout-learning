pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
//        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "MotionLayout Learning"
include(":app")
include(":lib")
include(":mylibrary")
