package com.serhiiromanchuk.settings.presentation.screens.preferences.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.SegmentOption
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.DropdownItem

enum class ExpensesFormat(override val label: String) : SegmentOption {
    MINUS("-$10"),
    PARENTHESES("($10)")
}

enum class CurrencyCategoryItem(override val title: String, val symbol: String) : DropdownItem {
    USD("US Dollar (USD)", "\$") {
        @Composable
        override fun Icon() { CurrencyIcon("\$") }
    },
    EUR("Euro (EUR)", "€") {
        @Composable
        override fun Icon() { CurrencyIcon("€") }
    },
    GBP("British Pound Sterling (GBP)", "£") {
        @Composable
        override fun Icon() { CurrencyIcon("£") }
    },
    JPY("Japanese Yen (JPY)", "¥") {
        @Composable
        override fun Icon() { CurrencyIcon("¥") }
    },
    CNY("Chinese Yuan Renminbi (CNY)", "¥") {
        @Composable
        override fun Icon() { CurrencyIcon("¥") }
    },
    INR("Indian Rupee (INR)", "₹") {
        @Composable
        override fun Icon() { CurrencyIcon("₹") }
    },
    ZAR("South African Rand (ZAR)", "R") {
        @Composable
        override fun Icon() { CurrencyIcon("R") }
    },
    CAD("Canadian Dollar (CAD)", "C\$") {
        @Composable
        override fun Icon() { CurrencyIcon("C\$") }
    },
    AUD("Australian Dollar (AUD)", "A\$") {
        @Composable
        override fun Icon() { CurrencyIcon("A\$") }
    },
    CHF("Swiss Franc (CHF)", "CHF") {
        @Composable
        override fun Icon() { CurrencyIcon("CHF") }
    },
}

enum class DecimalSeparator(override val label: String, val separator: String) : SegmentOption {
    POINT("1.00", "."),
    COMMA("1,00", ",")
}

enum class ThousandsSeparator(override val label: String, val separator: String) : SegmentOption {
    POINT("1.000", "."),
    COMMA("1,000", ","),
    SPACE("1 000", " ")
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