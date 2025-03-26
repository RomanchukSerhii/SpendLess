package com.serhiiromanchuk.core.domain.repository

interface SessionRepository {
    fun logIn(username: String)
    fun logOut()
    fun getLoggedInUsername(): String?
    fun startSession()
    suspend fun isSessionExpired(): Boolean
}