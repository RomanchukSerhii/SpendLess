package com.serhiiromanchuk.core.database.repository

import com.serhiiromanchuk.core.database.dao.TransactionDao
import com.serhiiromanchuk.core.database.mappers.toDomain
import com.serhiiromanchuk.core.database.mappers.toEntity
import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.core.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao
) : TransactionRepository {
    override fun getTransactionsByUser(userId: Long): Flow<List<Transaction>> {
        return transactionDao.getTransactionsByUser(userId).map { it.toDomain() }
    }

    override suspend fun upsertTransaction(transaction: Transaction) {
        transactionDao.upsertTransaction(transaction.toEntity())
    }

    override suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction.toEntity())
    }

    override suspend fun checkAndCreateRecurringTransactions() {
        val recurringTransactions = transactionDao.getRecurringTransactions().map { it.toDomain() }
        val today = Instant.now().toEpochMilli()

        for (transaction in recurringTransactions) {
            if (transaction.shouldRepeat(today)) {
                val newTransaction = transaction.copy(transactionDate = today)
                transactionDao.upsertTransaction(newTransaction.toEntity())
            }
        }
    }
}