package com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling

sealed interface OnboardingPrefAction {
    data class NavigateToDashboard(val username: String) : OnboardingPrefAction
}