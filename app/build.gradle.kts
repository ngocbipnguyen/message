plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.hilt)
//    alias(libs.plugins.kapt)
    id(libs.plugins.kapt.get().pluginId)
}

android {
    namespace = "com.example.message"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.message"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        dataBinding = true

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava2)
    implementation(libs.logging.interceptor)

    //rx
    implementation(libs.rxandroid)


    //navigator
    implementation(libs.navigation.fragmen)
    implementation(libs.navigation.navigation.ui)

}