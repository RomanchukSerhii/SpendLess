package com.serhiiromanchuk.core.domain.repository

import com.serhiiromanchuk.core.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun upsertUser(user: User)
    suspend fun getUser(username: String): User?
    fun getFlowUser(username: String): Flow<User?>
}