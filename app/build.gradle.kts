plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger)
}

android {
    namespace = "com.example.tuyasample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tuyasample"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }
    }

    packaging {
        resources {
            pickFirsts.add("lib/*/liblog.so")
            pickFirsts.add("lib/*/libc++_shared.so")
            pickFirsts.add("lib/*/libyuv.so")
            pickFirsts.add("lib/*/libopenh264.so")
            pickFirsts.add("lib/*/libv8wrapper.so")
            pickFirsts.add("lib/*/libv8android.so")
        }
    }

    signingConfigs {
        create("key") {
             // Default key password for debug
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("key")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    lint {
        abortOnError = false
    }
}

configurations.all {
    exclude(group = "com.thingclips.smart", module = "thingsmart-modularCampAnno")
}

dependencies {
    implementation(fileTree("libs") {
        include("*.aar")
    })

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.coroutines.android)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.retrofit.adapter)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp.logging)

    implementation(libs.glide)

    // Tuya SDK
    implementation(libs.thingsmart)

    // FastJSON for parsing
    implementation(libs.fastjson)

    // HTTP networking
    implementation(libs.okhttp.urlconnection)

    // Soloader (required dependency)
    implementation(libs.soloader)
}