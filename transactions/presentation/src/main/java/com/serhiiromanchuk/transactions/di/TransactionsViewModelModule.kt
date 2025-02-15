package com.serhiiromanchuk.transactions.di

import com.serhiiromanchuk.transactions.screens.TransactionsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val transactionsViewModelModule = module {
    viewModelOf(::TransactionsViewModel)
}