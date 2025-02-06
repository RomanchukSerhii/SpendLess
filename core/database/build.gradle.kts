plugins {
    alias(libs.plugins.spendless.android.library)
    alias(libs.plugins.spendless.android.room)
}

android {
    namespace = "com.serhiiromanchuk.core.database"
}

dependencies {
    implementation(projects.core.domain)
}