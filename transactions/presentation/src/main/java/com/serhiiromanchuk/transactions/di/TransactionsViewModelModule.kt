package com.serhiiromanchuk.transactions.di

import com.serhiiromanchuk.transactions.screens.TransactionsSharedViewModel
import com.serhiiromanchuk.transactions.utils.AmountFormatter
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val transactionsSharedViewModelModule = module {
    single { AmountFormatter() }

    viewModel { (username: String) -> TransactionsSharedViewModel(username, get(), get()) }
}