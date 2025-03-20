package com.serhiiromanchuk.transactions.utils

import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ThousandsSeparatorUi
import com.serhiiromanchuk.transactions.common_components.AmountSettings

object AmountFormatter {

    private const val MAX_AMOUNT_INTEGER_LENGTH = 9
    private const val MAX_NUMBER_OF_SEPARATORS = 2
    private const val MAX_DECIMAL_LENGTH = 2

    fun getFormatedAmount(
        newText: CharSequence,
        amountSettings: AmountSettings,
        enforceTwoDecimalPlaces: Boolean = false
    ): String {
        val decimalSeparator = amountSettings.decimalSeparator.separator
        val thousandSeparator = amountSettings.thousandsSeparator.separator

        var filteredText = newText.filter { it.isDigit() || it == '.' || it == ',' }

        filteredText = replaceDecimalSeparator(filteredText, decimalSeparator)
        val parts = filteredText.split(decimalSeparator)

        // Limit the length of the integer part to 9 digits (including the thousands separator)
        val limitedIntegerPart = getIntegerPart(parts[0], thousandSeparator)

        // Formatting the integer part with thousands separator
        val integerPart = formatThousands(limitedIntegerPart, thousandSeparator)

        // If there is a fractional part, limit it to two digits
        var resultText = integerPart
        if (parts.size > 1) {
            val fractionalPart = when {
                enforceTwoDecimalPlaces && parts[1].length == 1 -> parts[1] + "0"
                enforceTwoDecimalPlaces && parts[1].isEmpty() -> "00"
                else -> parts[1].take(MAX_DECIMAL_LENGTH)
            }

            resultText += decimalSeparator + fractionalPart
        }

        return resultText
    }

    fun parseAmountToFloat(
        amountText: CharSequence,
        amountSettings: AmountSettings,
        isExpense: Boolean
    ): Float {
        val decimalSeparator = amountSettings.decimalSeparator.separator
        val cleanedText = amountText.toString()
            .replace(amountSettings.thousandsSeparator.separator, "")
            .replace(decimalSeparator, ".")

        val amount =cleanedText.toFloatOrNull()
            ?: throw NumberFormatException("Invalid amount format: $amountText")

        return if (isExpense) -amount else amount
    }

    fun removeOldThousandsSeparator(text: CharSequence, decimalSeparator: String): CharSequence {
        val lastDecimalIndex = text.lastIndexOf(decimalSeparator)

        return if (lastDecimalIndex == -1) {
            text.toString().removeAllThousandSeparators()
        } else {
            val integerPart = text.substring(0, lastDecimalIndex)
            val decimalPart = text.substring(lastDecimalIndex)

            val cleanedIntegerPart = integerPart.removeAllThousandSeparators()
            cleanedIntegerPart + decimalPart
        }
    }

    private fun getIntegerPart(integerPart: String, thousandSeparator: String): String {
        val maxIntegerDigits = when (thousandSeparator) {
            ThousandsSeparatorUi.SPACE.separator -> MAX_AMOUNT_INTEGER_LENGTH
            else -> MAX_AMOUNT_INTEGER_LENGTH + MAX_NUMBER_OF_SEPARATORS
        }

        return integerPart.take(maxIntegerDigits)
    }

    private fun replaceDecimalSeparator(text: CharSequence, decimalSeparator: String): CharSequence {
        val lastIndex = text.lastIndexOfAny(
            listOf(
                DecimalSeparatorUi.POINT.separator,
                DecimalSeparatorUi.COMMA.separator
            )
        )

        if (lastIndex == -1 || lastIndex + 2 < text.length) return text

        val integerPart = text.substring(0, lastIndex)

        val decimalPart = text.substring(lastIndex + 1)

        return integerPart + decimalSeparator + decimalPart
    }

    private fun formatThousands(integerPart: String, thousandSeparator: String): String {
        val cleanIntegerPart = integerPart.replace(thousandSeparator, "")
        val reversed = cleanIntegerPart.reversed()
        val formatted = StringBuilder()

        for (i in reversed.indices) {
            if (i > 0 && i % 3 == 0) {
                formatted.append(thousandSeparator)
            }
            formatted.append(reversed[i])
        }
        return formatted.reverse().toString()
    }

    private fun String.removeAllThousandSeparators(): String {
        return this
            .replace(" ", "")
            .replace(".", "")
            .replace(",", "")
    }
}