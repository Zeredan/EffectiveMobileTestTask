plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
//    alias(libs.plugins.google.gms.google.services)
//    id("com.google.firebase.crashlytics")
}

android {
    namespace = "test.task.effectivemobile.testcourses"
    compileSdk = 35

    defaultConfig {
        applicationId = "test.task.effectivemobile.testcourses"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    flavorDimensions += "courses"
    productFlavors {
        create("real") {
            dimension = "courses"
        }
        create("mock") {
            dimension = "courses"
        }
    }
}

dependencies {
    implementation(project(":core:ui"))

    implementation(project(":feature"))
    implementation(project(":feature:main"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:login"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:account"))

    implementation(project(":domain:settings"))

    implementation(project(":data:settings:impl"))
    implementation(project(":data:courses_favorite:impl"))

    val flavorName = project.gradle.startParameter.taskNames.joinToString().let {
        when {
            it.contains("Mock", ignoreCase = true) -> "mock"
            it.contains("Real", ignoreCase = true) -> "real"
            else -> "real" // По умолчанию реальный
        }
    }
    when (flavorName) {
        "mock" -> implementation(project(":data:courses:mock"))
        "real" -> implementation(project(":data:courses:impl"))
    }

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.appcompat)
    implementation(project(":feature:settings"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.firebase.config)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

}