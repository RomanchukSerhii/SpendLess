package com.serhiiromanchuk.transactions.screens.dashboard.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ThousandsSeparatorUi
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.core.domain.entity.Expense
import com.serhiiromanchuk.core.domain.entity.Income
import com.serhiiromanchuk.core.domain.entity.RepeatType
import com.serhiiromanchuk.core.domain.entity.Transaction
import java.time.Instant

data class DashboardUiState(
    val transactions: Map<Instant, List<Transaction>> = createTestTransactions(),
    val isCreateTransactionOpen: Boolean = false,
    val accountInfoState: AccountInfoState = AccountInfoState(),
    val amountSettings: AmountSettings = AmountSettings(),
) {
    data class AccountInfoState(
        val balance: String = "$10,382.45",
        val popularCategory: ExpenseCategory = ExpenseCategory.FOOD,
        val largestTransactionTitle: String = "Adobe Yearly",
        val largestTransactionAmount: String = "-$59.99",
        val largestTransactionDate: String = "Jan 7, 2025",
        val previousWeekExpense: String = "-$762.20"
    )

    data class AmountSettings(
        val isExpense: Boolean = true,
        val currency: CurrencyCategoryItem = CurrencyCategoryItem.USD,
        val decimalSeparator: DecimalSeparatorUi = DecimalSeparatorUi.POINT,
        val thousandsSeparator: ThousandsSeparatorUi = ThousandsSeparatorUi.COMMA,
    )
}

fun createTestTransactions(): Map<Instant, List<Transaction>> {
    val today = Instant.now()
    val yesterday = today.minusSeconds(24 * 60 * 60) // Вчора

    val transactions =  listOf(
        // Транзакція типу Income, створена сьогодні
        Transaction(
            id = 0,
            userId = 0L,
            title = "Salary",
            amount = 1000f,
            transactionType = Income,
            repeatType = RepeatType.NOT_REPEAT,
            note = "Enjoyed a coffee and a snack at Starbucks with Rick and M.",
            transactionDate = today.toEpochMilli()
        ),
        // Транзакція типу Expense, створена сьогодні
        Transaction(
            id = 1,
            userId = 0L,
            title = "Food",
            amount = 50f,
            transactionType = Expense.FOOD,
            repeatType = RepeatType.NOT_REPEAT,
            note = "Enjoyed a coffee and a snack at Starbucks with Rick and M.",
            transactionDate = today.toEpochMilli()
        ),
        // Транзакція типу Expense, створена вчора
        Transaction(
            id = 2,
            userId = 0L,
            title = "Transport",
            amount = 30f,
            transactionType = Expense.TRANSPORTATION,
            repeatType = RepeatType.NOT_REPEAT,
            transactionDate = yesterday.toEpochMilli()
        ),
        // Транзакція типу Expense, створена вчора
        Transaction(
            id = 3,
            userId = 0L,
            title = "Entertainment",
            amount = 20f,
            transactionType = Expense.ENTERTAINMENT,
            repeatType = RepeatType.NOT_REPEAT,
            transactionDate = yesterday.toEpochMilli()
        ),
        // Транзакція типу Expense, створена вчора
        Transaction(
            id = 4,
            userId = 0L,
            title = "Clothing",
            amount = 80f,
            transactionType = Expense.CLOTHING,
            repeatType = RepeatType.NOT_REPEAT,
            transactionDate = yesterday.toEpochMilli()
        )
    )

    return transactions.groupBy { Instant.ofEpochMilli(it.transactionDate) }
}