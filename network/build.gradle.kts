plugins { alias(libs.plugins.android.library) }
android {
    namespace = "com.indianmesh.network"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig { minSdk = libs.versions.minSdk.get().toInt() }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    testImplementation(libs.bundles.testing.unit)
}
