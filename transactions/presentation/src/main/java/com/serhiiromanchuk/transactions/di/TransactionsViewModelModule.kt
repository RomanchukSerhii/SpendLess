package com.serhiiromanchuk.transactions.di

import com.serhiiromanchuk.transactions.screens.TransactionsSharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val transactionsSharedViewModelModule = module {
    viewModel { (username: String) -> TransactionsSharedViewModel(username, get()) }
}