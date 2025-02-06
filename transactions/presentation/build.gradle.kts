plugins {
    alias(libs.plugins.spendless.android.feature.ui)
}

android {
    namespace = "com.serhiiromanchuk.transactions.presentation"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.transactions.domain)
}