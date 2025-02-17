package com.serhiiromanchuk.settings.presentation.di

import com.serhiiromanchuk.settings.presentation.screens.preferences.PreferencesViewModel
import com.serhiiromanchuk.settings.presentation.screens.security.SecurityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsViewModelModule = module {
    viewModelOf(::PreferencesViewModel)
    viewModelOf(::SecurityViewModel)
}