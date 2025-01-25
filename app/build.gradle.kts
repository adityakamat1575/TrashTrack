plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.kairos.trashtrack"
    compileSdk = 35


    defaultConfig {
        applicationId = "com.kairos.trashtrack"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("""androidx.work:work-runtime:2.8.0""")
    implementation ("""com.google.android.gms:play-services-maps:17.0.1""")
    implementation ("""com.google.android.gms:play-services-location:17.0.0""")
    implementation ("""org.osmdroid:osmdroid-android:6.1.10""")


    // Import the BoM for the Firebase platform
    implementation(platform("""com.google.firebase:firebase-bom:33.8.0"""))
    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("""com.google.firebase:firebase-firestore""")

    implementation ("""com.github.bumptech.glide:glide:4.13.2""")  // Ensure you're using the latest version
    annotationProcessor ("""com.github.bumptech.glide:compiler:4.13.2""") // For Glide code generation

    //implementation ("""com.here.sdk:here-sdk-maps:4.9.3""")

    implementation ("""com.squareup.retrofit2:retrofit:2.9.0""")
    implementation ("""com.squareup.retrofit2:converter-gson:2.9.0""")
    implementation ("""com.squareup.okhttp3:logging-interceptor:4.9.2""")

    implementation ("""com.google.guava:guava:31.1-android""")

    implementation ("""com.google.firebase:firebase-firestore:24.8.0""")
    implementation ("""com.google.firebase:firebase-auth:22.1.0""") // If you use Firebase Authentication
    implementation ("""androidx.recyclerview:recyclerview:1.3.1""") // For displaying discussions
    implementation ("""com.google.android.material:material:1.8.0""") // For UI components


}



