package com.serhiiromanchuk.auth.presentation.di

import com.serhiiromanchuk.auth.presentation.screens.create_pin.CreatePinViewModel
import com.serhiiromanchuk.auth.presentation.screens.login.LoginViewModel
import com.serhiiromanchuk.auth.presentation.screens.onboarding_pref.OnboardingPrefViewModel
import com.serhiiromanchuk.auth.presentation.screens.registration.RegistrationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::CreatePinViewModel)
    viewModelOf(::OnboardingPrefViewModel)
}