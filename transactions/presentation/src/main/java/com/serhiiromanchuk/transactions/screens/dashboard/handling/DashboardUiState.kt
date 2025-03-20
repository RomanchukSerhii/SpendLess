package com.serhiiromanchuk.transactions.screens.dashboard.handling

import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.transactions.common_components.AmountSettings
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import java.time.Instant

data class DashboardUiState(
    val latestTransactions: Map<Instant, List<Transaction>> = emptyMap(),
    val isCreateTransactionOpen: Boolean = false,
    val accountInfoState: AccountInfoState = AccountInfoState(),
    val amountSettings: AmountSettings = AmountSettings(),
) {
    data class AccountInfoState(
        val balance: String = "\$0",
        val popularCategory: ExpenseCategory? = null,
        val largestTransaction: LargestTransaction? = null,
        val previousWeekExpenseAmount: String = "\$0"
    )

    data class LargestTransaction(
        val title: String = "",
        val amount: String = "",
        val date: String = "",
    )
}