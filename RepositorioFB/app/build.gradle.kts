plugins {

    id("com.android.application")

    id("org.jetbrains.kotlin.android")

    id("com.google.gms.google-services")
}

android {
    namespace = "com.kimberly.repositoriofb"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kimberly.repositoriofb"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.13.0"))

    // Realtime Database
    implementation("com.google.firebase:firebase-database")

}