// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Need to update as per the kotlin version
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false

    // hilt
    id("com.google.dagger.hilt.android") version "2.55" apply false

    // navigation safeargs
    id("androidx.navigation.safeargs") version "2.8.6" apply false
}