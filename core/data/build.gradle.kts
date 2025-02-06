plugins {
    alias(libs.plugins.spendless.android.library)
}

android {
    namespace = "com.serhiiromanchuk.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}