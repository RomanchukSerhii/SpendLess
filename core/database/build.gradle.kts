plugins {
    alias(libs.plugins.spendless.android.library)
}

android {
    namespace = "com.serhiiromanchuk.core.database"
}

dependencies {
    implementation(projects.core.domain)
}