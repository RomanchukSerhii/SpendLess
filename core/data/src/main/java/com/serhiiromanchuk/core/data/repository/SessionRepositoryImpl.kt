package com.serhiiromanchuk.core.data.repository

import android.content.SharedPreferences
import android.os.SystemClock
import com.serhiiromanchuk.core.domain.entity.LockedOutDuration
import com.serhiiromanchuk.core.domain.entity.SessionExpiryDuration
import com.serhiiromanchuk.core.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val preferences: SharedPreferences
) : SessionRepository {

    companion object {
        private const val KEY_SESSION_TIMESTAMP = "session_timestamp"
        private const val KEY_USERNAME = "username"
        private const val KEY_SESSION_EXPIRY_DURATION = "session_expiry_duration"
        private const val FIFTEEN_SEC_MILLIS = 15 * 1000L
        private const val THIRTY_SEC_MILLIS = 30 * 1000L
        private const val MINUTE_IN_MILLIS = 60 * 1000L
        private const val HOUR_IN_MILLIS = 60 * MINUTE_IN_MILLIS
        private const val KEY_PIN_LOCK_TIMESTAMP = "pin_lock_timestamp"
        private const val KEY_PIN_LOCK_DURATION = "pin_lock_duration"
    }

    override fun logIn(username: String) {
        preferences.edit()
            .putString(KEY_USERNAME, username)
            .apply()
    }

    override fun logOut() {
        preferences.edit()
            .remove(KEY_USERNAME)
            .remove(KEY_SESSION_TIMESTAMP)
            .remove(KEY_SESSION_EXPIRY_DURATION)
            .apply()
    }

    override fun getLoggedInUsername(): String? {
        return preferences.getString(KEY_USERNAME, null)
    }

    override fun isUserLoggedIn(): Boolean {
        return getLoggedInUsername() != null
    }

    override fun startSession(sessionExpiryDuration: SessionExpiryDuration) {
        val duration = when (sessionExpiryDuration) {
            SessionExpiryDuration.FIVE_MIN -> 5 * MINUTE_IN_MILLIS
            SessionExpiryDuration.FIFTEEN_MIN -> 15 * MINUTE_IN_MILLIS
            SessionExpiryDuration.THIRTY_MIN -> 30 * MINUTE_IN_MILLIS
            SessionExpiryDuration.HOUR -> HOUR_IN_MILLIS
        }

        preferences.edit()
            .putLong(KEY_SESSION_TIMESTAMP, SystemClock.elapsedRealtime())
            .putLong(KEY_SESSION_EXPIRY_DURATION, duration)
            .apply()
    }

    override fun isSessionExpired(): Boolean {
        val currentTime = SystemClock.elapsedRealtime()
        val lastAuthTime = preferences.getLong(KEY_SESSION_TIMESTAMP, 0L)
        val sessionExpiryDuration = preferences.getLong(KEY_SESSION_EXPIRY_DURATION, 0L)

        return (currentTime - lastAuthTime) > sessionExpiryDuration
    }

    override fun setPinLockTimestamp(lockedOutDuration: LockedOutDuration) {
        val lockDurationMillis = when (lockedOutDuration) {
            LockedOutDuration.FIFTEEN_SEC -> FIFTEEN_SEC_MILLIS
            LockedOutDuration.THIRTY_SEC -> THIRTY_SEC_MILLIS
            LockedOutDuration.ONE_MIN -> MINUTE_IN_MILLIS
            LockedOutDuration.FIVE_MIN -> 5 * MINUTE_IN_MILLIS
        }

        preferences.edit()
            .putLong(KEY_PIN_LOCK_TIMESTAMP, SystemClock.elapsedRealtime())
            .putLong(KEY_PIN_LOCK_DURATION, lockDurationMillis)
            .apply()
    }

    override fun getPinLockRemainingTime(): Int {
        val lockDurationMillis = preferences.getLong(KEY_PIN_LOCK_DURATION, 0L)
        val lockStartTime = preferences.getLong(KEY_PIN_LOCK_TIMESTAMP, 0L)

        if (lockDurationMillis == 0L || lockStartTime == 0L) return 0 // If no lock is set, return 0

        val elapsedTime = SystemClock.elapsedRealtime() - lockStartTime

        return ((lockDurationMillis - elapsedTime).coerceAtLeast(0L) / 1000).toInt()
    }

    override fun restorePinLockTimestamp() {
        preferences.edit()
            .remove(KEY_PIN_LOCK_TIMESTAMP)
            .remove(KEY_PIN_LOCK_DURATION)
            .apply()
    }
}