import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.safeargs)
}

val minSdkVersion = rootProject.extra["minSdkVersion"] as Int

android {
    namespace = "com.example.motionlayoutlearning"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.motionlayoutlearning"
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                "proguard-rules.pro",
                file("${project.layout.buildDirectory}/generated/proguard-rules/navargs-proguard-rules.pro")
            )
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    applicationVariants.configureEach {
        outputs.withType(BaseVariantOutputImpl::class).configureEach {
            val outputVersionName = versionName + if (buildType.isDebuggable) "_test" else ""
            outputFileName = "BSBBank_$outputVersionName.apk"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    implementation(project(":lib"))
    implementation(project(":mylibrary"))
    implementation("ru.rambler.android:swipe-layout:1.0.17")

    implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.2")
}