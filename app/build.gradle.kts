plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jillestchuh.ktlint)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "net.mouazkaadan.inshort"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = "17"
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.9"
        }
    }
    namespace = "net.mouazkaadan.inshort"
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.lifecycleViewmodelKtx)
    implementation(libs.lifecycle.runtime.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidTestExtJunit)
    androidTestImplementation(libs.androidTestEspressoCore)

    // hilt
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)

    // Navigation
    implementation(libs.navigationCompose)
    implementation(libs.hiltNavigationCompose)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converterGson)
    implementation(libs.loggingInterceptor)

    // coroutine
    implementation(libs.kotlinxCoroutinesAndroid)
    implementation(libs.kotlinxCoroutinesCore)

    // Room
    implementation(libs.roomRuntime)
    ksp(libs.roomCompiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.roomKtx)

    // compose
    implementation(platform(libs.composeBom))
    implementation(libs.composeUi)
    implementation(libs.composeMaterial3)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.activityCompose)
    debugImplementation(libs.composeUiTooling)

    implementation(libs.runtimeLivedata)

    // Coil
    implementation(libs.coilCompose)
}
