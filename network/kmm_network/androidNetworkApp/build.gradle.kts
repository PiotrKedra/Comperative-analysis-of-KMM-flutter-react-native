plugins {
    id("com.android.application")
    kotlin("android")
    // kapt is annotation processing lib, but in future kotlin will switch to Kotlin Symbol Processing API (KSP)
    // but for now KSP does not support Hilt, so we stay with kapt
    kotlin("kapt")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

val composeVersion = "1.1.1"

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.example.kmm_network.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
//    these 4 belows are needed for jetpack compose
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.1.1")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.1.1")
    // Material Design
    implementation("androidx.compose.material:material:1.1.1")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.1.1")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.1.1")
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.4.2")
    // ConstraintLayout (needed for nested Row, Column, Box
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0")
    // Others
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.animation:animation:1.1.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    // mb i need also compiler jetpack compose lib, we will see ...

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")

    // for detecting memory leak
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")

}