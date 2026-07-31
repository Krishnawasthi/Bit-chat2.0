plugins { alias(libs.plugins.android.library) }

android {
    namespace = "com.indianmesh.crypto"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(libs.bouncycastle.provider) // If needed, though Android Keystore often suffices
    testImplementation(libs.bundles.testing.unit)
}
