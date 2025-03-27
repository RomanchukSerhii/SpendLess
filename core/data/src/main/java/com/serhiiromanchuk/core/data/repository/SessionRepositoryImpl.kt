package com.serhiiromanchuk.core.data.repository

import android.content.SharedPreferences
import android.os.SystemClock
import com.serhiiromanchuk.core.domain.entity.SessionExpiryDuration
import com.serhiiromanchuk.core.domain.entity.User
import com.serhiiromanchuk.core.domain.exception.UserNotLoggedInException
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import com.serhiiromanchuk.core.domain.repository.UserRepository

class SessionRepositoryImpl(
    private val preferences: SharedPreferences,
    private val  userRepository: UserRepository
) : SessionRepository {

    companion object {
        private const val KEY_SESSION_TIMESTAMP = "session_timestamp"
        private const val KEY_USERNAME = "username"
        private const val MINUTE_IN_MILLIS = 60 * 1000L
        private const val HOUR_IN_MILLIS = 60 * MINUTE_IN_MILLIS
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
            .apply()
    }

    override fun getLoggedInUsername(): String? {
        return preferences.getString(KEY_USERNAME, null)
    }

    override fun startSession() {
        preferences.edit()
            .putLong(KEY_SESSION_TIMESTAMP, SystemClock.elapsedRealtime())
            .apply()
    }

    override suspend fun isSessionExpired(): Boolean {
        val currentTime = SystemClock.elapsedRealtime()
        val lastAuthTime = preferences.getLong(KEY_SESSION_TIMESTAMP, 0L)
        val user = getUser()
            ?: throw UserNotLoggedInException()

        val sessionDurationLong = when (user.settings.sessionExpiryDuration) {
            SessionExpiryDuration.FIVE_MIN -> 5000
            SessionExpiryDuration.FIFTEEN_MIN -> 5 * MINUTE_IN_MILLIS
            SessionExpiryDuration.THIRTY_MIN -> 30 * MINUTE_IN_MILLIS
            SessionExpiryDuration.HOUR -> HOUR_IN_MILLIS
        }

        return (currentTime - lastAuthTime) > sessionDurationLong
    }

    private suspend fun getUser(): User? {
        val username = preferences.getString(KEY_USERNAME, null)
        if (username.isNullOrEmpty()) return null

        return userRepository.getUser(username)
    }
}