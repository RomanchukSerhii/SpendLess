package com.serhiiromanchuk.settings.presentation.di

import com.serhiiromanchuk.settings.presentation.screens.SettingsSharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsViewModelModule = module {
    viewModel { SettingsSharedViewModel(get(), get()) }
}