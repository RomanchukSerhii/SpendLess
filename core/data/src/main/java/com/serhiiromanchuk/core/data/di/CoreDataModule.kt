package com.serhiiromanchuk.core.data.di

import android.content.Context
import android.content.SharedPreferences
import com.serhiiromanchuk.core.data.repository.SessionRepositoryImpl
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDataModule = module {
    single { provideSharedPreferences(androidContext()) }
    single<SessionRepository> { SessionRepositoryImpl(get()) }
}

fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
}