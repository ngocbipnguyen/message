plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.hilt)
    id(libs.plugins.kapt.get().pluginId)
    alias(libs.plugins.google.gms.google.services)
//    alias(libs.plugins.safeargs.kotlin)
    alias(libs.plugins.androidx.navigation.safeargs)

}

android {
    namespace = "com.example.message"
    compileSdk = 35

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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


    // firebase
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.messaging)


    //google identity
    implementation(libs.credentials)
//    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)

    //coroutines
    implementation(libs.lifecycle.viewmodel.ktx)

    // glide
    implementation(libs.glide)
    implementation(libs.circleimageview)

    //serialization
    implementation(libs.kotlinx.serialization.runtime)
    implementation(libs.library.oauth2.http)

}