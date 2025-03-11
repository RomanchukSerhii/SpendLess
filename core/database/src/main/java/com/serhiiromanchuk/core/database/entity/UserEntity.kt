package com.serhiiromanchuk.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val username: String,
    val pin: String,

    val expensesFormat: String,
    val currency: String,
    val decimalSeparator: String,
    val thousandsSeparator: String,
    val biometricsPrompt: String,
    val sessionExpiryDuration: String,
    val lockedOutDuration: String
)
