package com.serhiiromanchuk.core.database.mappers

import androidx.room.TypeConverter
import com.serhiiromanchuk.core.domain.entity.Expense
import com.serhiiromanchuk.core.domain.entity.Income
import com.serhiiromanchuk.core.domain.entity.RepeatType
import com.serhiiromanchuk.core.domain.entity.TransactionType

object TransactionConverter {
    private const val INCOME_TYPE = "Income"

    @TypeConverter
    fun fromRepeatType(value: RepeatType): String = value.name

    @TypeConverter
    fun toRepeatType(value: String): RepeatType = enumValueOf(value)

    @TypeConverter
    fun fromTransactionType(value: TransactionType): String {
        return when (value) {
            is Income -> INCOME_TYPE
            is Expense -> value.name
        }
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return when (value) {
            INCOME_TYPE -> Income
            else -> enumValueOf<Expense>(value)
        }
    }
}