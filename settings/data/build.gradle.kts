plugins {
    alias(libs.plugins.spendless.android.library)
}

android {
    namespace = "com.serhiiromanchuk.settings.data"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.koin.android.workmanager)
    implementation(libs.kotlinx.serialization.json)

    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.settings.domain)
}