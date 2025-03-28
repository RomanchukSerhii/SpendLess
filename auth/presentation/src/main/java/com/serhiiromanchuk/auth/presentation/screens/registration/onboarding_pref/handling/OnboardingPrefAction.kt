package com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling

sealed interface OnboardingPrefAction {
    data object NavigateToTransactions : OnboardingPrefAction
}