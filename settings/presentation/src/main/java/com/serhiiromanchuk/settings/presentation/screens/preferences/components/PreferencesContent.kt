package com.serhiiromanchuk.settings.presentation.screens.preferences.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.SegmentedButton
import com.serhiiromanchuk.core.presentation.designsystem.components.select.SelectCategory
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.settings.presentation.R
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiState

@Composable
fun PreferencesContent(
    state: PreferencesUiState,
    onEvent: (PreferencesUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExpensesFormatSettings(
            selectedFormat = state.expensesFormat,
            onOptionClick = { onEvent(PreferencesUiEvent.ExpensesFormatClicked(it)) }
        )
        CurrencySettings(
            selectedCurrency = state.currency,
            onCurrencySelected = { onEvent(PreferencesUiEvent.CurrencyClicked(it)) }
        )
        DecimalSeparatorSettings(
            selectedDecimal = state.decimalSeparator,
            onOptionClick = { onEvent(PreferencesUiEvent.DecimalSeparatorClicked(it)) }
        )
        ThousandsSeparatorSettings(
            selectedThousands = state.thousandsSeparator,
            onOptionClick = { onEvent(PreferencesUiEvent.ThousandsSeparatorClicked(it)) }
        )
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
    onCurrencySelected: (CurrencyCategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.currency),
        modifier = modifier
    ) {
        SelectCategory(
            items = CurrencyCategoryItem.entries,
            selectedItem = selectedCurrency,
            onItemSelected = { onCurrencySelected(it as CurrencyCategoryItem) }
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
        title = "Thousands separator",
        modifier = modifier
    ) {
        SegmentedButton(
            segmentOptions = ThousandsSeparator.entries,
            selectedOption = selectedThousands,
            onOptionClick = { onOptionClick(it as ThousandsSeparator) }
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFEF7FF
)
@Composable
private fun PreviewPreferencesContent() {
    SpendLessTheme {
        PreferencesContent(
            state = PreferencesUiState(),
            onEvent = {}
        )
    }
}