package com.serhiiromanchuk.transactions.screens.dashboard.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.SpendCategoryItem

data class DashboardUiState(
    val isCreateTransactionSheetOpened: Boolean = false,
    val accountInfoState: AccountInfoState = AccountInfoState()
) {
    data class AccountInfoState(
        val balance: String = "$10,382.45",
        val popularCategory: SpendCategoryItem = SpendCategoryItem.FOOD,
        val largestTransactionTitle: String = "Adobe Yearly",
        val largestTransactionAmount: String = "-$59.99",
        val largestTransactionDate: String = "Jan 7, 2025",
        val previousWeekExpense: String = "-$762.20"
    )
}