package com.serhiiromanchuk.spendless

import android.app.Application
import com.serhiiromanchuk.auth.presentation.di.authenticationViewModelModule
import com.serhiiromanchuk.settings.presentation.di.settingsViewModelModule
import com.serhiiromanchuk.spendless.di.appModule
import com.serhiiromanchuk.transactions.di.transactionsViewModelModule
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
                authenticationViewModelModule,
                settingsViewModelModule,
                transactionsViewModelModule
            )
        }
    }
}