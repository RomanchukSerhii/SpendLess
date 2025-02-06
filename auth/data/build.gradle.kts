plugins {
    alias(libs.plugins.spendless.android.library)
}

android {
    namespace = "com.serhiiromanchuk.auth.data"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}