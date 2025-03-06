package com.serhiiromanchuk.core.presentation.designsystem.components.currency_format

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.R
import com.serhiiromanchuk.core.presentation.designsystem.components.OptionText
import com.serhiiromanchuk.core.presentation.designsystem.components.SegmentOption
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.DropdownItem

enum class ExpensesFormat(override val label: @Composable () -> Unit) : SegmentOption {
    MINUS(
        label = {
            OptionText(text = stringResource(R.string._10))
        }
    ),
    PARENTHESES(
        label = {
            OptionText(text = stringResource(R.string.parentheses_10))
        }
    )
}

enum class CurrencyCategoryItem(override val title: String, val symbol: String) : DropdownItem {
    USD("US Dollar (USD)", "\$") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("\$")
        }
    },
    EUR("Euro (EUR)", "€") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("€")
        }
    },
    GBP("British Pound Sterling (GBP)", "£") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("£")
        }
    },
    JPY("Japanese Yen (JPY)", "¥") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("¥")
        }
    },
    CNY("Chinese Yuan Renminbi (CNY)", "¥") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("¥")
        }
    },
    INR("Indian Rupee (INR)", "₹") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("₹")
        }
    },
    ZAR("South African Rand (ZAR)", "R") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("R")
        }
    },
    CAD("Canadian Dollar (CAD)", "C\$") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("C\$")
        }
    },
    AUD("Australian Dollar (AUD)", "A\$") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("A\$")
        }
    },
    CHF("Swiss Franc (CHF)", "CHF") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            CurrencyIcon("CHF")
        }
    },
}

enum class DecimalSeparator(
    override val label: @Composable () -> Unit, val separator: String
) : SegmentOption {
    POINT(label = { OptionText("1.00") }, "."),
    COMMA(label = { OptionText("1,00") }, ",")
}

enum class ThousandsSeparator(
    override val label: @Composable () -> Unit, val separator: String
) : SegmentOption {
    POINT(label = { OptionText("1.000") }, "."),
    COMMA(label = { OptionText("1,000") }, ","),
    SPACE(label = { OptionText("1 000") }, " ")
}

@Composable
private fun CurrencyIcon(
    currency: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    Text(
        text = currency,
        modifier = modifier.padding(start = 16.dp),
        style = MaterialTheme.typography.labelMedium.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize
        )
    )
}