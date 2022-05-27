plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

val ktorVersion= "2.0.1"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosNetworkApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("com.squareup.sqldelight:runtime:1.5.3")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("com.squareup.sqldelight:android-driver:1.5.3")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:1.5.3")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1-native-mt"){
                    version {
                        strictly("1.6.1-native-mt")
                    }
                }
            }
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

sqldelight {
    database("UserDatabase") {
        packageName = "com.example.kmm_network.datasource.cache"
        sourceFolders = listOf("sqldelight")
    }
}