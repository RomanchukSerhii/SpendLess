package com.serhiiromanchuk.spendless

import android.app.Application
import com.google.android.play.core.ktx.BuildConfig
import com.serhiiromanchuk.auth.data.di.authDataModule
import com.serhiiromanchuk.auth.presentation.di.authViewModelModule
import com.serhiiromanchuk.core.data.di.coreDataModule
import com.serhiiromanchuk.core.database.di.databaseModule
import com.serhiiromanchuk.settings.presentation.di.settingsViewModelModule
import com.serhiiromanchuk.spendless.di.appModule
import com.serhiiromanchuk.transactions.data.transactionsDataModule
import com.serhiiromanchuk.transactions.di.transactionsSharedViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class SpendLessApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

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
}