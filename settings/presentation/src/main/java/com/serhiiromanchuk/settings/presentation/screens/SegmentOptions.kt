package com.serhiiromanchuk.settings.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.OptionText
import com.serhiiromanchuk.core.presentation.designsystem.components.SegmentOption
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.DropdownItem
import com.serhiiromanchuk.settings.presentation.R

enum class ExpensesFormat(override val label: @Composable () -> Unit) : SegmentOption {
    MINUS(
        label = {
            OptionText(text = stringResource(R.string._10))
        }
    ),
    PARENTHESES(
        label = {
            OptionText(text = stringResource(R.string.parentneses_10))
        }
    )
}

enum class CurrencyCategoryItem(override val title: String, val symbol: String) : DropdownItem {
    USD("US Dollar (USD)", "\$") {
        @Composable
        override fun Icon() {
            CurrencyIcon("\$")
        }
    },
    EUR("Euro (EUR)", "€") {
        @Composable
        override fun Icon() {
            CurrencyIcon("€")
        }
    },
    GBP("British Pound Sterling (GBP)", "£") {
        @Composable
        override fun Icon() {
            CurrencyIcon("£")
        }
    },
    JPY("Japanese Yen (JPY)", "¥") {
        @Composable
        override fun Icon() {
            CurrencyIcon("¥")
        }
    },
    CNY("Chinese Yuan Renminbi (CNY)", "¥") {
        @Composable
        override fun Icon() {
            CurrencyIcon("¥")
        }
    },
    INR("Indian Rupee (INR)", "₹") {
        @Composable
        override fun Icon() {
            CurrencyIcon("₹")
        }
    },
    ZAR("South African Rand (ZAR)", "R") {
        @Composable
        override fun Icon() {
            CurrencyIcon("R")
        }
    },
    CAD("Canadian Dollar (CAD)", "C\$") {
        @Composable
        override fun Icon() {
            CurrencyIcon("C\$")
        }
    },
    AUD("Australian Dollar (AUD)", "A\$") {
        @Composable
        override fun Icon() {
            CurrencyIcon("A\$")
        }
    },
    CHF("Swiss Franc (CHF)", "CHF") {
        @Composable
        override fun Icon() {
            CurrencyIcon("CHF")
        }
    },
}

enum class DecimalSeparator(override val label: @Composable () -> Unit, val separator: String) :
    SegmentOption {
    POINT(label = { OptionText("1.00") }, "."),
    COMMA(label = { OptionText("1,00") }, ",")
}

enum class ThousandsSeparator(override val label: @Composable () -> Unit, val separator: String) :
    SegmentOption {
    POINT(label = { OptionText("1.000") }, "."),
    COMMA(label = { OptionText("1,000") }, ","),
    SPACE(label = { OptionText("1 000") }, " ")
}

enum class BiometricsPrompt(override val label: @Composable () -> Unit) : SegmentOption {
    ENABLE(
        label = {
            OptionText(text = stringResource(R.string.enable))
        }
    ),
    DISABLE(
        label = {
            OptionText(text = stringResource(R.string.disable))
        }
    )
}

enum class SessionExpiryDuration(override val label: @Composable () -> Unit) : SegmentOption {
    FIVE_MIN(label = { OptionText("5 min") }),
    FIFTEEN_MIN(label = { OptionText("15 min") }),
    THIRTY_MIN(label = { OptionText("30 min") }),
    HOUR(label = { OptionText("1 hour") })
}

enum class LockedOutDuration(override val label: @Composable () -> Unit) : SegmentOption {
    FIFTEEN_SEC(label = { OptionText("15s") }),
    THIRTY_SEC(label = { OptionText("30s") }),
    ONE_MIN(label = { OptionText("1 min") }),
    FIVE_MIN(label = { OptionText("5 min") })
}

@Composable
private fun CurrencyIcon(
    currency: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = currency,
        modifier = modifier.padding(start = 16.dp),
        style = MaterialTheme.typography.labelMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}