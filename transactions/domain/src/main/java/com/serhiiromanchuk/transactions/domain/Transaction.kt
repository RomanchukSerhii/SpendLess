package com.serhiiromanchuk.transactions.domain

import java.time.Instant

data class Transaction(
    val id: Long = 0L,
    val user: String,
    val title: String,
    val amount: Float,
    val transactionType: TransactionType,
    val note: String? = null,
    val creationTimestamp: Instant = Instant.now()
)

sealed interface TransactionType

enum class Expense : TransactionType {
    HOME,
    FOOD,
    ENTERTAINMENT,
    CLOTHING,
    HEALTH,
    PERSONAL_CARE,
    TRANSPORTATION,
    EDUCATION,
    SAVING,
    OTHER
}

data object Income : TransactionType