package com.serhiiromanchuk.core.domain.repository

import com.serhiiromanchuk.core.domain.entity.LockedOutDuration
import com.serhiiromanchuk.core.domain.entity.SessionExpiryDuration

interface SessionRepository {
    fun logIn(username: String)
    fun logOut()
    fun getLoggedInUsername(): String?
    fun isUserLoggedIn(): Boolean
    fun startSession(sessionExpiryDuration: SessionExpiryDuration)
    fun isSessionExpired(): Boolean
    fun setPinLockTimestamp(lockedOutDuration: LockedOutDuration)
    fun getPinLockRemainingTime(): Int
    fun restorePinLockTimestamp()
    fun setLaunchedFromWidget(value: Boolean)
    fun isLaunchedFromWidget(): Boolean
}