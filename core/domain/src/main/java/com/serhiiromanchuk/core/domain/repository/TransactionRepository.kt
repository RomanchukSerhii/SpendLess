package com.serhiiromanchuk.core.domain.repository

import com.serhiiromanchuk.core.domain.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactionsByUser(userId: Long): Flow<List<Transaction>>
    suspend fun upsertTransaction(transaction: Transaction)
    suspend fun delete(transaction: Transaction)
}