plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.dailyexpenses"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dailyexpenses"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"

    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material3:material3:${libs.versions.material3.get()}")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")


    // Room (SQLite)
    implementation(libs.room.runtime) // ✅
    implementation(libs.room.ktx)     // ✅
    kapt(libs.room.compiler)


    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}
