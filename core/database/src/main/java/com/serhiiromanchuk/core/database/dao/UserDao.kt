package com.serhiiromanchuk.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.serhiiromanchuk.core.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertDao(user: UserEntity)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUser(username: String): UserEntity?

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    fun getFlowUser(username: String): Flow<UserEntity?>
}