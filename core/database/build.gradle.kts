plugins {
    alias(libs.plugins.spendless.android.library)
    alias(libs.plugins.spendless.android.room)
}

android {
    namespace = "com.serhiiromanchuk.core.database"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(projects.core.domain)
}