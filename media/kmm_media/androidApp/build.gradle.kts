plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.example.kmm_media.android"
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

val composeVersion = "1.1.1"

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    // UI
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    // material design
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    // Activity
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.1")

    implementation("androidx.camera:camera-camera2:1.1.0-alpha11")
    implementation("androidx.camera:camera-view:1.0.0-alpha31")
    implementation("androidx.camera:camera-lifecycle:1.1.0-alpha11")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.6.0-RC")

    implementation("io.coil-kt:coil-compose:1.4.0")

    implementation("com.google.android.exoplayer:exoplayer:2.16.1")

}
