import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            // Compose UI
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Coroutines
            implementation(libs.kotlinx.coroutines.android)

            // Dependency Injection
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            // ktor
            implementation(libs.ktor.client.okhttp)
            
            // Splash Screen
            implementation("androidx.core:core-splashscreen:1.0.1")
            
            // Browser for Custom Tabs
            implementation("androidx.browser:browser:1.8.0")
        }
        
        commonMain.dependencies {
            // Compose Core UI
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.materialIconsExtended)

            // ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // AndroidX Libraries
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // Navigation
            implementation(libs.navigation.compose)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Date & Time
            implementation(libs.kotlinx.datetime)

            // Dependency Injection (Koin)
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Data Storage
            implementation(libs.datastore.preferences)
            implementation(libs.stately.common)
            implementation(libs.atomic.fu)

            // Logging
            api(libs.logging)

            // Image loading
            implementation(libs.coil.compose)
        }
        
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        
        iosMain.dependencies {
            // ktor
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.tibadev.alimansour.prophetstories"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        buildFeatures {
            buildConfig = true
        }
        applicationId = "com.tibadev.alimansour.prophetstories"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 11
        versionName = "2.5.4"
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Debug Tools
    debugImplementation(compose.uiTooling)
}
