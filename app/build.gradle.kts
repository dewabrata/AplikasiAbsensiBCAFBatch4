plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.juaracoding.aplikasiabsensi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.juaracoding.aplikasiabsensi"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField ("String", "API_KEY", '"'+project.properties["x-api-key"].toString()+'"')
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildFeatures {
            buildConfig = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.picasso)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //apache word reader
    implementation("org.apache.poi:poi:5.2.3")
    implementation("org.apache.poi:poi-ooxml:5.2.3")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

}