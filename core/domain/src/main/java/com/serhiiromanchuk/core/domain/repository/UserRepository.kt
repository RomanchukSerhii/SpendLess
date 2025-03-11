package com.serhiiromanchuk.core.domain.repository

import com.serhiiromanchuk.core.domain.entity.User

interface UserRepository {
    suspend fun upsertUser(user: User)
    suspend fun getUser(username: String): User?
}