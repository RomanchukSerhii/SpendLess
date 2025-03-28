package com.serhiiromanchuk.transactions.utils

import com.serhiiromanchuk.core.domain.entity.Expense
import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.ui.InstantFormatter
import com.serhiiromanchuk.transactions.common_components.AmountSettings
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.time.Instant
import java.time.temporal.ChronoUnit

object TransactionAnalytics {

    suspend fun getAccountInfoState(
        transactions: List<Transaction>,
        amountSettings: AmountSettings
    ): DashboardUiState.AccountInfoState = coroutineScope {

        val balanceDeferred =
            async { formatAmount(getAccountBalance(transactions), amountSettings) }

        val largestTransactionAmountDeferred =
            async { formatAmount(getLargestTransactionAmount(transactions), amountSettings) }

        val previousWeekExpenseAmountDeferred =
            async { formatAmount(getPreviousWeekExpenseAmount(transactions), amountSettings) }

        val largestTransactionDateDeferred =
            async { InstantFormatter.formatDateString(getLargestTransactionDate(transactions)) }

        val popularCategoryDeferred = async { getMostPopularCategory(transactions) }

        val largestTransactionTitleDeferred = async { getLargestTransactionName(transactions) }

        DashboardUiState.AccountInfoState(
            balance = balanceDeferred.await(),
            popularCategory = popularCategoryDeferred.await(),
            largestTransaction = DashboardUiState.LargestTransaction(
                title = largestTransactionTitleDeferred.await(),
                amount = largestTransactionAmountDeferred.await(),
                date = largestTransactionDateDeferred.await()
            ),
            previousWeekExpenseAmount = previousWeekExpenseAmountDeferred.await()
        )
    }

    fun getAccountBalance(transactions: List<Transaction>): Float {
        requireNonEmptyTransactions(transactions)

        return transactions.fold(0f) { aac, transaction ->
            aac + transaction.amount
        }
    }

    fun getMostPopularCategory(transactions: List<Transaction>): ExpenseCategory {
        requireNonEmptyTransactions(transactions)

        return transactions
            .filter { it.transactionType is Expense }
            .groupingBy { it.transactionType as Expense }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key?.toUi()
            ?: throw IllegalStateException("No expense categories found in transactions")
    }

    fun getLargestTransactionAmount(transactions: List<Transaction>): Float {
        requireNonEmptyTransactions(transactions)

        return transactions
            .filter { it.transactionType is Expense }
            .minOfOrNull { it.amount } ?: throw IllegalStateException("No transactions found")
    }

    fun getLargestTransactionName(transactions: List<Transaction>): String {
        requireNonEmptyTransactions(transactions)

        return transactions
            .filter { it.transactionType is Expense }
            .minByOrNull { it.amount }
            ?.title ?: throw IllegalStateException("No transactions found")
    }

    fun getLargestTransactionDate(transactions: List<Transaction>): Long {
        requireNonEmptyTransactions(transactions)

        return transactions
            .filter { it.transactionType is Expense }
            .minByOrNull { it.amount }
            ?.transactionDate ?: throw IllegalStateException("No transactions found")
    }

    fun getPreviousWeekExpenseAmount(transactions: List<Transaction>): Float {
        requireNonEmptyTransactions(transactions)

        val (lastMonday, lastSunday) = InstantFormatter.getPreviousWeekRange()

        return transactions
            .filter { it.transactionType is Expense }
            .filter {
                val transactionDate = Instant.ofEpochMilli(it.transactionDate)
                transactionDate.isAfter(
                    lastMonday.minus(
                        1,
                        ChronoUnit.DAYS
                    )
                ) && transactionDate.isBefore(lastSunday.plus(1, ChronoUnit.DAYS))
            }
            .fold(0f) { aac, transaction ->
                aac + transaction.amount
            }
    }

    fun getLatestTransactions(transactions: List<Transaction>): List<Transaction> {
        requireNonEmptyTransactions(transactions)

        return transactions
            .sortedByDescending { it.transactionDate }
            .take(20)
    }

    private fun formatAmount(
        amount: Float,
        amountSettings: AmountSettings
    ): String {
        val formattedAmount = AmountFormatter.getFormatedAmount(
            amount = amount,
            amountSettings = amountSettings,
            enforceTwoDecimalPlaces = true
        )
        return if (amount < 0) {
            when (amountSettings.expensesFormat) {
                ExpensesFormatUi.MINUS -> "-\$$formattedAmount"
                ExpensesFormatUi.PARENTHESES -> "(\$$formattedAmount)"
            }
        } else "\$$formattedAmount"
    }

    private fun requireNonEmptyTransactions(transactions: List<Transaction>) {
        require(transactions.isNotEmpty()) { "Transactions list is empty or null!" }
    }
}