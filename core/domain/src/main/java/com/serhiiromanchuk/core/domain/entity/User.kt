package com.serhiiromanchuk.core.domain.entity

data class User(
    val id: Long = 0L,
    val username: String,
    val pin: String,
    val settings: UserSettings
)