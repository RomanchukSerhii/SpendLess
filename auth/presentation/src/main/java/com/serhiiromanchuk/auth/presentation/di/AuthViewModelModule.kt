package com.serhiiromanchuk.auth.presentation.di

import androidx.lifecycle.SavedStateHandle
import com.serhiiromanchuk.auth.presentation.screens.login.LoginViewModel
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.PinPromptViewModel
import com.serhiiromanchuk.auth.presentation.screens.registration.RegistrationSharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationSharedViewModel)
    viewModel {
        PinPromptViewModel(get(), get())
    }

    viewModel { (savedStateHandle: SavedStateHandle) ->
        RegistrationSharedViewModel(
            userDataValidator = get(),
            savedStateHandle = savedStateHandle,
            userRepository = get(),
            sessionRepository = get()
        )
    }
}