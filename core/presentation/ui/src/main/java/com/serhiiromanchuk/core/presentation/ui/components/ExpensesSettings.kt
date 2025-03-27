package com.serhiiromanchuk.core.presentation.ui.components

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
import com.serhiiromanchuk.core.presentation.designsystem.components.AppSegmentedButton
import com.serhiiromanchuk.core.presentation.designsystem.components.select.SelectCategory
import com.serhiiromanchuk.core.presentation.ui.states.ExpensesFormatState

@Composable
fun ExpensesSettings(
    expensesFormatState: ExpensesFormatState,
    onExpensesFormatClick: (ExpensesFormatUi) -> Unit,
    onCurrencyClick: (CurrencyCategoryItem) -> Unit,
    onDecimalSeparatorClick: (DecimalSeparatorUi) -> Unit,
    onThousandsSeparatorClick: (ThousandsSeparatorUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FormattingExample(
            formattingValue = expensesFormatState.formattingExample,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ExpensesFormatSettings(
            selectedFormat = expensesFormatState.expensesFormat,
            onOptionClick = onExpensesFormatClick
        )

        CurrencySettings(
            selectedCurrency = expensesFormatState.currency,
            onOptionClick = onCurrencyClick
        )

        DecimalSeparatorSettings(
            selectedDecimal = expensesFormatState.decimalSeparator,
            onOptionClick = onDecimalSeparatorClick
        )

        ThousandsSeparatorSettings(
            selectedThousands = expensesFormatState.thousandsSeparator,
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
    selectedFormat: ExpensesFormatUi,
    onOptionClick: (ExpensesFormatUi) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.expenses_format),
        modifier = modifier
    ) {
        AppSegmentedButton(
            segmentOptions = ExpensesFormatUi.entries,
            selectedOption = selectedFormat,
            onOptionClick = { onOptionClick(it as ExpensesFormatUi) }
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
            onItemSelected = { onOptionClick(it as CurrencyCategoryItem) },
            iconSize = 24.dp
        )
    }
}

@Composable
private fun DecimalSeparatorSettings(
    selectedDecimal: DecimalSeparatorUi,
    onOptionClick: (DecimalSeparatorUi) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.decimal_separator),
        modifier = modifier
    ) {
        AppSegmentedButton(
            segmentOptions = DecimalSeparatorUi.entries,
            selectedOption = selectedDecimal,
            onOptionClick = { onOptionClick(it as DecimalSeparatorUi) }
        )
    }
}

@Composable
private fun ThousandsSeparatorSettings(
    selectedThousands: ThousandsSeparatorUi,
    onOptionClick: (ThousandsSeparatorUi) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.thousands_separator),
        modifier = modifier
    ) {
        AppSegmentedButton(
            segmentOptions = ThousandsSeparatorUi.entries,
            selectedOption = selectedThousands,
            onOptionClick = { onOptionClick(it as ThousandsSeparatorUi) }
        )
    }
}