plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // for ksp
    id("com.google.devtools.ksp")

    // for hilt
    id("com.google.dagger.hilt.android")

    // safe args for kotlin only modules
    id("androidx.navigation.safeargs.kotlin")
    
    // to add parcelize
    id("kotlin-parcelize")

    // Kotlin serialization plugin for type safe routes and navigation arguments
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.sanai.gokart"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sanai.gokart"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\"${project.properties["BASE_URL"]}\"")
        buildConfigField("String", "USE_DUMMY_RESPONSE", "\"${project.properties["USE_DUMMY_RESPONSE"]}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OkHttp logging interceptor
    implementation(libs.logging.interceptor)

    // Dagger
    implementation(libs.dagger)
    implementation(libs.androidx.navigation.ui.ktx)
    ksp(libs.dagger.compiler)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Room database
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Glide
    implementation(libs.glide)

    // navigation component Views/Fragments integration
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment.ktx)

    // Generic dependencies
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)

    // Test cases
    testImplementation(libs.junit)
    testImplementation(libs.mockwebserver)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}