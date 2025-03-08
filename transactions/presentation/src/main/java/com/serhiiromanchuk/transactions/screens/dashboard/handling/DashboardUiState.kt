package com.serhiiromanchuk.transactions.screens.dashboard.handling

import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.domain.Expense
import com.serhiiromanchuk.transactions.domain.Income
import com.serhiiromanchuk.transactions.domain.Transaction
import java.time.Instant

data class DashboardUiState(
    val transactions: Map<Instant, List<Transaction>> = createTestTransactions(),
    val isCreateTransactionSheetOpened: Boolean = false,
    val accountInfoState: AccountInfoState = AccountInfoState()
) {
    data class AccountInfoState(
        val balance: String = "$10,382.45",
        val popularCategory: ExpenseCategory = ExpenseCategory.FOOD,
        val largestTransactionTitle: String = "Adobe Yearly",
        val largestTransactionAmount: String = "-$59.99",
        val largestTransactionDate: String = "Jan 7, 2025",
        val previousWeekExpense: String = "-$762.20"
    )
}

fun createTestTransactions(): Map<Instant, List<Transaction>> {
    val today = Instant.now()
    val yesterday = today.minusSeconds(24 * 60 * 60) // Вчора

    val transactions =  listOf(
        // Транзакція типу Income, створена сьогодні
        Transaction(
            id = 0,
            user = "User1",
            title = "Salary",
            amount = 1000f,
            transactionType = Income,
            note = "Enjoyed a coffee and a snack at Starbucks with Rick and M.",
            creationTimestamp = today
        ),
        // Транзакція типу Expense, створена сьогодні
        Transaction(
            id = 1,
            user = "User1",
            title = "Food",
            amount = 50f,
            transactionType = Expense.FOOD,
            note = "Enjoyed a coffee and a snack at Starbucks with Rick and M.",
            creationTimestamp = today
        ),
        // Транзакція типу Expense, створена вчора
        Transaction(
            id = 2,
            user = "User1",
            title = "Transport",
            amount = 30f,
            transactionType = Expense.TRANSPORTATION,
            creationTimestamp = yesterday
        ),
        // Транзакція типу Expense, створена вчора
        Transaction(
            id = 3,
            user = "User1",
            title = "Entertainment",
            amount = 20f,
            transactionType = Expense.ENTERTAINMENT,
            creationTimestamp = yesterday
        ),
        // Транзакція типу Expense, створена вчора
        Transaction(
            id = 4,
            user = "User1",
            title = "Clothing",
            amount = 80f,
            transactionType = Expense.CLOTHING,
            creationTimestamp = yesterday
        )
    )

    return transactions.groupBy { it.creationTimestamp }
}