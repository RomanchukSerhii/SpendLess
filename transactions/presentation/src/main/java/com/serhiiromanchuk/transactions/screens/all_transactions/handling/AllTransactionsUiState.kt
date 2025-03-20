package com.serhiiromanchuk.transactions.screens.all_transactions.handling

import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.transactions.common_components.AmountSettings
import java.time.Instant

data class AllTransactionsUiState(
    val transactions: Map<Instant, List<Transaction>> = emptyMap(),
    val amountSettings: AmountSettings = AmountSettings()
)
