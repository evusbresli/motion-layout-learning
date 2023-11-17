// Top-level build file where you can add configuration options common to all subprojects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.safeargs) apply false
}

buildscript {
    extra["minSdkVersion"] = 24
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}