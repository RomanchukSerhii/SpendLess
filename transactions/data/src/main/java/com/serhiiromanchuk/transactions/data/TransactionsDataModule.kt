package com.serhiiromanchuk.transactions.data

import com.serhiiromanchuk.transactions.domain.CsvExporter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val transactionsDataModule = module {
    single<CsvExporter> { CsvExporterImpl(androidContext()) }
}