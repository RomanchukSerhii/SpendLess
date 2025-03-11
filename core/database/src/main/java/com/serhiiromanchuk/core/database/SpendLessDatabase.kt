package com.serhiiromanchuk.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.serhiiromanchuk.core.database.dao.UserDao
import com.serhiiromanchuk.core.database.entity.UserEntity
import com.serhiiromanchuk.core.database.mappers.SettingsConverter

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(SettingsConverter::class)
abstract class SpendLessDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}