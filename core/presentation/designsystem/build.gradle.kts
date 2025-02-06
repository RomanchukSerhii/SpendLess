plugins {
    alias(libs.plugins.spendless.android.library.compose)
}

android {
    namespace = "com.serhiiromanchuk.core.presentation.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(libs.androidx.material3)
}