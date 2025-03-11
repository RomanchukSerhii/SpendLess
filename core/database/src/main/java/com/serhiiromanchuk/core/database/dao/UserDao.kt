package com.serhiiromanchuk.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.serhiiromanchuk.core.database.entity.UserEntity

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertDao(user: UserEntity)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUser(username: String): UserEntity?
}