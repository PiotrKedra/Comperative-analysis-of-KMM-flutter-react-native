pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "PreappKMM"
include(":androidPreeappKMM")
include(":shared")

//enableFeaturePreview("GRADLE_METADATA")