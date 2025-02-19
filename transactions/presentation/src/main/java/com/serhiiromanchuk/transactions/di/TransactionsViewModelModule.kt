package com.serhiiromanchuk.transactions.di

import com.serhiiromanchuk.transactions.screens.create_transaction.CreateTransactionViewModel
import com.serhiiromanchuk.transactions.screens.dashboard.DashboardViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val transactionsViewModelModule = module {
    viewModelOf(::CreateTransactionViewModel)
    viewModelOf(::DashboardViewModel)
}