package com.serhiiromanchuk.transactions.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.serhiiromanchuk.core.domain.repository.TransactionRepository
import org.koin.mp.KoinPlatform.getKoin

class RecurringTransactionWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {
    private val transactionRepository: TransactionRepository by lazy { getKoin().get() }

    override suspend fun doWork(): Result {
        transactionRepository.checkAndCreateRecurringTransactions()
        return Result.success()
    }
}
