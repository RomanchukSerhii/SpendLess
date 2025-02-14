package com.serhiiromanchuk.settings.presentation.di

import com.serhiiromanchuk.settings.presentation.screens.preferences.PreferencesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsViewModelModule = module {
    viewModelOf(::PreferencesViewModel)
}