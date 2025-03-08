package com.serhiiromanchuk.core.presentation.designsystem.components.currency_format

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.R
import com.serhiiromanchuk.core.presentation.designsystem.components.AppCard
import com.serhiiromanchuk.core.presentation.designsystem.components.SegmentedButton
import com.serhiiromanchuk.core.presentation.designsystem.components.SettingItem
import com.serhiiromanchuk.core.presentation.designsystem.components.select.SelectCategory

@Composable
fun CurrencyFormatSettings(
    currencyFormatState: CurrencyFormatState,
    onExpensesFormatClick: (ExpensesFormat) -> Unit,
    onCurrencyClick: (CurrencyCategoryItem) -> Unit,
    onDecimalSeparatorClick: (DecimalSeparator) -> Unit,
    onThousandsSeparatorClick: (ThousandsSeparator) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FormattingExample(
            formattingValue = currencyFormatState.formattingExample,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ExpensesFormatSettings(
            selectedFormat = currencyFormatState.expensesFormat,
            onOptionClick = onExpensesFormatClick
        )

        CurrencySettings(
            selectedCurrency = currencyFormatState.currency,
            onOptionClick = onCurrencyClick
        )

        DecimalSeparatorSettings(
            selectedDecimal = currencyFormatState.decimalSeparator,
            onOptionClick = onDecimalSeparatorClick
        )

        ThousandsSeparatorSettings(
            selectedThousands = currencyFormatState.thousandsSeparator,
            onOptionClick = onThousandsSeparatorClick
        )
    }
}

@Composable
private fun FormattingExample(
    formattingValue: String,
    modifier: Modifier = Modifier
) {
    AppCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = formattingValue,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "spend this month",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Composable
private fun ExpensesFormatSettings(
    selectedFormat: ExpensesFormat,
    onOptionClick: (ExpensesFormat) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.expenses_format),
        modifier = modifier
    ) {
        SegmentedButton(
            segmentOptions = ExpensesFormat.entries,
            selectedOption = selectedFormat,
            onOptionClick = { onOptionClick(it as ExpensesFormat) }
        )
    }
}

@Composable
private fun CurrencySettings(
    selectedCurrency: CurrencyCategoryItem,
    onOptionClick: (CurrencyCategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.currency),
        modifier = modifier
    ) {
        SelectCategory(
            items = CurrencyCategoryItem.entries,
            selectedItem = selectedCurrency,
            onItemSelected = { onOptionClick(it as CurrencyCategoryItem) }
        )
    }
}

@Composable
private fun DecimalSeparatorSettings(
    selectedDecimal: DecimalSeparator,
    onOptionClick: (DecimalSeparator) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.decimal_separator),
        modifier = modifier
    ) {
        SegmentedButton(
            segmentOptions = DecimalSeparator.entries,
            selectedOption = selectedDecimal,
            onOptionClick = { onOptionClick(it as DecimalSeparator) }
        )
    }
}

@Composable
private fun ThousandsSeparatorSettings(
    selectedThousands: ThousandsSeparator,
    onOptionClick: (ThousandsSeparator) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.thousands_separator),
        modifier = modifier
    ) {
        SegmentedButton(
            segmentOptions = ThousandsSeparator.entries,
            selectedOption = selectedThousands,
            onOptionClick = { onOptionClick(it as ThousandsSeparator) }
        )
    }
}