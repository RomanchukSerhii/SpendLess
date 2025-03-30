package com.serhiiromanchuk.transactions.utils

import com.serhiiromanchuk.core.domain.entity.Expense
import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.core.domain.util.InstantFormatter
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.transactions.common_components.AmountSettings
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState
import com.serhiiromanchuk.transactions.screens.export_transactions.handling.ExportRange
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
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

    fun getLatestTransactions(transactions: List<Transaction>): List<Transaction> {
        requireNonEmptyTransactions(transactions)

        return transactions
            .sortedByDescending { it.transactionDate }
            .take(20)
    }

    fun filterTransactionsByRange(
        transactions: List<Transaction>,
        range: ExportRange
    ): List<Transaction> {
        val zoneId = ZoneId.systemDefault()
        val now = LocalDate.now(zoneId)
        val startDate: Instant

        when (range) {
            ExportRange.LAST_MONTH -> {
                val lastMonth = now.minusMonths(1)
                startDate = lastMonth.withDayOfMonth(1).atStartOfDay(zoneId).toInstant()
            }

            ExportRange.THREE_MONTH -> {
                val threeMonthsAgo = now.minusMonths(3)
                startDate = threeMonthsAgo.withDayOfMonth(1).atStartOfDay(zoneId).toInstant()
            }

            ExportRange.CURRENT_MONTH -> {
                startDate = now.withDayOfMonth(1).atStartOfDay(zoneId).toInstant()
            }

            ExportRange.ALL -> {
                startDate = Instant.MIN
            }
        }

        return transactions
            .filter {
                val transactionInstant = Instant.ofEpochMilli(it.transactionDate)
                transactionInstant in startDate..Instant.now()
            }
            .reversed()
    }

    private fun getAccountBalance(transactions: List<Transaction>): Float {
        requireNonEmptyTransactions(transactions)

        return transactions.fold(0f) { aac, transaction ->
            aac + transaction.amount
        }
    }

    private fun getMostPopularCategory(transactions: List<Transaction>): ExpenseCategory {
        requireNonEmptyTransactions(transactions)

        return transactions
            .filter { it.transactionType is Expense }
            .groupingBy { it.transactionType as Expense }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key?.toUi()
            ?: throw IllegalStateException("No expense categories found in transactions")
    }

    private fun getLargestTransactionAmount(transactions: List<Transaction>): Float {
        requireNonEmptyTransactions(transactions)

        return transactions
            .filter { it.transactionType is Expense }
            .minOfOrNull { it.amount } ?: throw IllegalStateException("No transactions found")
    }

    private fun getLargestTransactionName(transactions: List<Transaction>): String {
        requireNonEmptyTransactions(transactions)

        return transactions
            .filter { it.transactionType is Expense }
            .minByOrNull { it.amount }
            ?.title ?: throw IllegalStateException("No transactions found")
    }

    private fun getLargestTransactionDate(transactions: List<Transaction>): Long {
        requireNonEmptyTransactions(transactions)

        return transactions
            .filter { it.transactionType is Expense }
            .minByOrNull { it.amount }
            ?.transactionDate ?: throw IllegalStateException("No transactions found")
    }

    private fun getPreviousWeekExpenseAmount(transactions: List<Transaction>): Float {
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

    private fun formatAmount(
        amount: Float,
        amountSettings: AmountSettings
    ): String {
        val formattedAmount = AmountFormatter.getFormatedAmount(
            amount = amount,
            amountSettings = amountSettings,
            enforceTwoDecimalPlaces = true
        )
        val currency = amountSettings.currency.symbol
        return if (amount < 0) {
            when (amountSettings.expensesFormat) {
                ExpensesFormatUi.MINUS -> "-$currency$formattedAmount"
                ExpensesFormatUi.PARENTHESES -> "($currency$formattedAmount)"
            }
        } else "$currency$formattedAmount"
    }

    private fun requireNonEmptyTransactions(transactions: List<Transaction>) {
        require(transactions.isNotEmpty()) { "Transactions list is empty or null!" }
    }
}