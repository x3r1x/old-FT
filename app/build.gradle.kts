plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.fitnesstogether"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.fitnesstogether"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Retrofit - основной клиент
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Конвертер для JSON (используем Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Если вы используете Kotlin Coroutines, добавьте это
    // implementation("com.squareup.retrofit2:converter-scalars:2.9.0") // Опционально, для строк/примитивов
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1") // Пример версии
    // implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2") // Для Call.await()

}