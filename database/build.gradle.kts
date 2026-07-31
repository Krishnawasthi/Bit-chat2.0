plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.indianmesh.database"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    
    implementation(libs.room.runtime)
    implementation(libs.room.rxjava3)
    ksp(libs.room.compiler)
    
    // SQLCipher
    implementation(libs.sqlcipher)
    implementation(libs.sqlite.framework)

    testImplementation(libs.bundles.testing.unit)
}
