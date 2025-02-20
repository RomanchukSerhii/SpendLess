package com.serhiiromanchuk.auth.presentation.di

import com.serhiiromanchuk.auth.presentation.screens.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authenticationViewModelModule = module {
    viewModelOf(::LoginViewModel)
}