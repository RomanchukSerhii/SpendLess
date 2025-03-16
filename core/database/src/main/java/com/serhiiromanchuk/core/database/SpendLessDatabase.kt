package com.serhiiromanchuk.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.serhiiromanchuk.core.database.dao.TransactionDao
import com.serhiiromanchuk.core.database.dao.UserDao
import com.serhiiromanchuk.core.database.entity.TransactionEntity
import com.serhiiromanchuk.core.database.entity.UserEntity
import com.serhiiromanchuk.core.database.mappers.SettingsConverter
import com.serhiiromanchuk.core.database.mappers.TransactionConverter

@Database(entities = [UserEntity::class, TransactionEntity::class], version = 1)
@TypeConverters(SettingsConverter::class, TransactionConverter::class)
abstract class SpendLessDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao
}