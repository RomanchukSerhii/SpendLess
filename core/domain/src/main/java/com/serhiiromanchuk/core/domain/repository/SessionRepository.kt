package com.serhiiromanchuk.core.domain.repository

import com.serhiiromanchuk.core.domain.entity.LockedOutDuration

interface SessionRepository {
    fun logIn(username: String)
    fun logOut()
    fun getLoggedInUsername(): String?
    fun isUserLoggedIn(): Boolean
    fun startSession()
    suspend fun isSessionExpired(): Boolean
    fun setPinLockTimestamp(lockedOutDuration: LockedOutDuration)
    fun getPinLockRemainingTime(): Int
    fun restorePinLockTimestamp()
}