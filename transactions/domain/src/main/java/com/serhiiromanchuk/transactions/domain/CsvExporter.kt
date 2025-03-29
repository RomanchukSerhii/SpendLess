package com.serhiiromanchuk.transactions.domain

import com.serhiiromanchuk.core.domain.entity.Transaction

interface CsvExporter {
    fun exportToCsv(fileName: String, transactions: List<Transaction>)
}