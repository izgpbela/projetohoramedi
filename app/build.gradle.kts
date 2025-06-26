plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.lembretedemedicamentos"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lembretedemedicamentos"
        minSdk = 26
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Room (para o banco de dados) - SINTAXE CORRIGIDA
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version") // KSP é o recomendado hoje em dia

    // RecyclerView (para a lista) - SINTAXE CORRIGIDA
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}
