plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.kotlin.ksp)
}

android {
    compileSdk = 36
    defaultConfig {
        applicationId = "com.tibadev.alimansour.prophetstories"
        minSdk = 23
        targetSdk = 36
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        versionCode = project.findProperty("VERSION_CODE")?.toString()?.toInt() ?: 12
        versionName = project.findProperty("VERSION_NAME")?.toString() ?: "2.5.5"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-kotlin-serialization.pro",
            )
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.tibadev.alimansour.prophetstories"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
    implementation(libs.timber)
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    ksp(libs.koin.compiler)

    implementation(libs.androidx.core)
    implementation(libs.bundles.lifecycle)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coil)
    implementation(libs.navigation.compose)
    implementation(libs.bundles.appcompat)
    implementation(libs.app.update)
    implementation(libs.browser)
    implementation(libs.multidex)
    implementation(libs.splashScreen)

    testImplementation(libs.bundles.domain.test)
    androidTestImplementation(libs.bundles.app.test)
    debugImplementation(libs.test.compose.ui.test.junit4)
}

kotlin {
    jvmToolchain(JavaVersion.VERSION_21.majorVersion.toInt())
}
