package com.serhiiromanchuk.core.database.repository

import com.serhiiromanchuk.core.database.dao.UserDao
import com.serhiiromanchuk.core.database.mappers.toDomain
import com.serhiiromanchuk.core.database.mappers.toEntity
import com.serhiiromanchuk.core.domain.entity.User
import com.serhiiromanchuk.core.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun upsertUser(user: User) {
        userDao.upsertDao(user.toEntity())
    }

    override suspend fun getUser(username: String): User? {
        return userDao.getUser(username)?.toDomain()
    }
}