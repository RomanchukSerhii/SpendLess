package com.serhiiromanchuk.core.database.di

import androidx.room.Room
import com.serhiiromanchuk.core.database.SpendLessDatabase
import com.serhiiromanchuk.core.database.repository.UserRepositoryImpl
import com.serhiiromanchuk.core.domain.repository.UserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            SpendLessDatabase::class.java,
            "spend_less.db"
        ).build()
    }
    single { get<SpendLessDatabase>().userDao() }

    single<UserRepository> { UserRepositoryImpl(get()) }
}