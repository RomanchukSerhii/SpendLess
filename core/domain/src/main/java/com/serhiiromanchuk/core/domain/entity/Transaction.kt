package com.serhiiromanchuk.core.domain.entity

import java.time.Instant

data class Transaction(
    val id: Long = 0L,
    val userId: Long,
    val title: String,
    val amount: Float,
    val repeatType: RepeatType,
    val transactionType: TransactionType,
    val note: String? = null,
    val transactionDate: Long = Instant.now().toEpochMilli()
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

enum class RepeatType {
    NOT_REPEAT,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}