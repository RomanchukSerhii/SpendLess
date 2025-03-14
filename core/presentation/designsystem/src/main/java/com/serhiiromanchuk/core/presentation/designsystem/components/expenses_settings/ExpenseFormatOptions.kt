package com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings

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
import com.serhiiromanchuk.core.presentation.designsystem.components.select.DropdownItem

enum class ExpensesFormatUi(override val label: @Composable () -> Unit) : SegmentOption {
    MINUS(
        label = {
            OptionText(text = stringResource(R.string._10))
        }
    ),
    PARENTHESES(
        label = {
            OptionText(text = stringResource(R.string.parentheses_10))
        }
    );
}

enum class CurrencyCategoryItem(override val titleRes: Int, val symbol: String) : DropdownItem {
    USD(R.string.us_dollar_usd, "\$") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "\$",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    EUR(R.string.euro_eur, "€") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "€",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    GBP(R.string.british_pound_sterling_gbp, "£") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "£",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    JPY(R.string.japanese_currency, "¥") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "¥",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    CNY(R.string.chinese_currency, "¥") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "¥",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    INR(R.string.indian_currency, "₹") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "₹",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    ZAR(R.string.south_african_currency, "R") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "R",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    CAD(R.string.canadian_currency, "C\$") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "C\$",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    AUD(R.string.australian_currency, "A\$") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "A\$",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    CHF(R.string.swiss_currency, "CHF") {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            CurrencyIcon(
                currency = "CHF",
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
}

enum class DecimalSeparatorUi(
    override val label: @Composable () -> Unit, val separator: String
) : SegmentOption {
    POINT(label = { OptionText("1.00") }, "."),
    COMMA(label = { OptionText("1,00") }, ",")
}

enum class ThousandsSeparatorUi(
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