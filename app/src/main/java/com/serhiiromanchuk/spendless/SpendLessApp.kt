package com.serhiiromanchuk.spendless

import android.app.Application
import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.play.core.ktx.BuildConfig
import com.serhiiromanchuk.auth.data.di.authDataModule
import com.serhiiromanchuk.auth.presentation.di.authViewModelModule
import com.serhiiromanchuk.core.data.di.coreDataModule
import com.serhiiromanchuk.core.database.di.databaseModule
import com.serhiiromanchuk.settings.presentation.di.settingsViewModelModule
import com.serhiiromanchuk.spendless.di.appModule
import com.serhiiromanchuk.transactions.data.RecurringTransactionWorker
import com.serhiiromanchuk.transactions.data.transactionsDataModule
import com.serhiiromanchuk.transactions.di.transactionsSharedViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import java.time.Duration
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

class SpendLessApp : Application() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setupWorManager(this@SpendLessApp)

        startKoin {
            androidLogger()
            androidContext(this@SpendLessApp)
            modules(
                appModule,
                authViewModelModule,
                authDataModule,
                databaseModule,
                settingsViewModelModule,
                transactionsDataModule,
                transactionsSharedViewModelModule,
                coreDataModule
            )
        }
    }

    private fun setupWorManager(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<RecurringTransactionWorker>(
            Duration.ofHours(24),
            Duration.ofHours(1)
        )
            .setInitialDelay(Duration.ofMillis(getInitialDelay()))
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "recurringTransactionWork",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    private fun getInitialDelay(): Long {
        val now = ZonedDateTime.now(ZoneId.systemDefault())
        val nextRunTime = now.with(LocalTime.of(2, 0, 0))

        val adjustedNextRunTime = if (now.isAfter(nextRunTime)) {
            nextRunTime.plusDays(1)
        } else {
            nextRunTime
        }

        return Duration.between(now, adjustedNextRunTime).toMillis()
    }
}