package com.serhiiromanchuk.auth.presentation.di

import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.CreatePinViewModel
import com.serhiiromanchuk.auth.presentation.screens.login.LoginViewModel
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.OnboardingPrefViewModel
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.PinPromptViewModel
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.CreateUsernameViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::CreateUsernameViewModel)
    viewModelOf(::CreatePinViewModel)
    viewModelOf(::OnboardingPrefViewModel)
    viewModelOf(::PinPromptViewModel)
}