package com.serhiiromanchuk.core.database.mappers

import android.util.Base64
import com.serhiiromanchuk.core.database.entity.UserEntity
import com.serhiiromanchuk.core.database.security.Crypto
import com.serhiiromanchuk.core.domain.entity.ExpensesFormat
import com.serhiiromanchuk.core.domain.entity.Currency
import com.serhiiromanchuk.core.domain.entity.DecimalSeparator
import com.serhiiromanchuk.core.domain.entity.ThousandsSeparator
import com.serhiiromanchuk.core.domain.entity.BiometricsPrompt
import com.serhiiromanchuk.core.domain.entity.SessionExpiryDuration
import com.serhiiromanchuk.core.domain.entity.LockedOutDuration
import com.serhiiromanchuk.core.domain.entity.User
import com.serhiiromanchuk.core.domain.entity.UserSettings

fun User.toEntity(): UserEntity {
    val encryptedPin = Crypto.encrypt(this.pin.toByteArray())
    val encryptedPinBase64 = Base64.encodeToString(encryptedPin, Base64.DEFAULT)

    return UserEntity(
        id = this.id,
        username = this.username,
        pin = encryptedPinBase64,
        expensesFormat = this.settings.expensesFormat.name,
        currency = this.settings.currency.name,
        decimalSeparator = this.settings.decimalSeparator.name,
        thousandsSeparator = this.settings.thousandsSeparator.name,
        biometricsPrompt = this.settings.biometricsPrompt.name,
        sessionExpiryDuration = this.settings.sessionExpiryDuration.name,
        lockedOutDuration = this.settings.lockedOutDuration.name
    )
}

fun UserEntity.toDomain(): User {
    val decryptedPinBytes = Crypto.decrypt(Base64.decode(this.pin, Base64.DEFAULT))
    val decryptedPin = String(decryptedPinBytes)
    return User(
        id = this.id,
        username = this.username,
        pin = decryptedPin,
        settings = UserSettings(
            expensesFormat = ExpensesFormat.valueOf(this.expensesFormat),
            currency = Currency.valueOf(this.currency),
            decimalSeparator = DecimalSeparator.valueOf(this.decimalSeparator),
            thousandsSeparator = ThousandsSeparator.valueOf(this.thousandsSeparator),
            biometricsPrompt = BiometricsPrompt.valueOf(this.biometricsPrompt),
            sessionExpiryDuration = SessionExpiryDuration.valueOf(this.sessionExpiryDuration),
            lockedOutDuration = LockedOutDuration.valueOf(this.lockedOutDuration)
        )
    )
}