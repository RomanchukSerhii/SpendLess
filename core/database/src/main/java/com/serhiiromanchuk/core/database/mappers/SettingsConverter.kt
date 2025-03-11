package com.serhiiromanchuk.core.database.mappers

import androidx.room.TypeConverter
import com.serhiiromanchuk.core.domain.entity.BiometricsPrompt
import com.serhiiromanchuk.core.domain.entity.Currency
import com.serhiiromanchuk.core.domain.entity.DecimalSeparator
import com.serhiiromanchuk.core.domain.entity.ExpensesFormat
import com.serhiiromanchuk.core.domain.entity.LockedOutDuration
import com.serhiiromanchuk.core.domain.entity.SessionExpiryDuration
import com.serhiiromanchuk.core.domain.entity.ThousandsSeparator

class SettingsConverter {

    @TypeConverter
    fun fromExpensesFormat(value: ExpensesFormat): String {
        return value.name
    }

    @TypeConverter
    fun toExpensesFormat(value: String): ExpensesFormat {
        return ExpensesFormat.valueOf(value)
    }

    @TypeConverter
    fun fromCurrency(value: Currency): String {
        return value.name
    }

    @TypeConverter
    fun toCurrency(value: String): Currency {
        return Currency.valueOf(value)
    }

    @TypeConverter
    fun fromDecimalSeparator(value: DecimalSeparator): String {
        return value.name
    }

    @TypeConverter
    fun toDecimalSeparator(value: String): DecimalSeparator {
        return DecimalSeparator.valueOf(value)
    }

    @TypeConverter
    fun fromThousandsSeparator(value: ThousandsSeparator): String {
        return value.name
    }

    @TypeConverter
    fun toThousandsSeparator(value: String): ThousandsSeparator {
        return ThousandsSeparator.valueOf(value)
    }

    @TypeConverter
    fun fromBiometricsPrompt(value: BiometricsPrompt): String {
        return value.name
    }

    @TypeConverter
    fun toBiometricsPrompt(value: String): BiometricsPrompt {
        return BiometricsPrompt.valueOf(value)
    }

    @TypeConverter
    fun fromSessionExpiryDuration(value: SessionExpiryDuration): String {
        return value.name
    }

    @TypeConverter
    fun toSessionExpiryDuration(value: String): SessionExpiryDuration {
        return SessionExpiryDuration.valueOf(value)
    }

    @TypeConverter
    fun fromLockedOutDuration(value: LockedOutDuration): String {
        return value.name
    }

    @TypeConverter
    fun toLockedOutDuration(value: String): LockedOutDuration {
        return LockedOutDuration.valueOf(value)
    }
}