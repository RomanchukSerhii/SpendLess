package com.serhiiromanchuk.auth.presentation.di

import com.serhiiromanchuk.auth.presentation.screens.login.LoginViewModel
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.PinPromptViewModel
import com.serhiiromanchuk.auth.presentation.screens.registration.RegistrationSharedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::PinPromptViewModel)
    viewModelOf(::RegistrationSharedViewModel)
}