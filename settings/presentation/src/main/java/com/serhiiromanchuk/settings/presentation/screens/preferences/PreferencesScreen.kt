package com.serhiiromanchuk.settings.presentation.screens.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTopBar
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.components.FilledButton
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ExpensesSettings
import com.serhiiromanchuk.settings.presentation.R
import com.serhiiromanchuk.settings.presentation.screens.SettingsSharedViewModel
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PreferencesScreenRoot(
    onBackClick: () -> Unit,
    viewModel: SettingsSharedViewModel = koinViewModel()
) {
    BaseContentLayout(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.preferences),
                onBackClick = onBackClick
            )
        }
    ) {
        PreferencesScreen(
            state = viewModel.preferencesState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
private fun PreferencesScreen(
    state: PreferencesUiState,
    onEvent: (PreferencesUiEvent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ExpensesSettings(
            expensesFormatState = state.expensesFormatState,
            onExpensesFormatClick = { onEvent(PreferencesUiEvent.ExpensesFormatClicked(it)) },
            onCurrencyClick = { onEvent(PreferencesUiEvent.CurrencyClicked(it)) },
            onDecimalSeparatorClick = { onEvent(PreferencesUiEvent.DecimalSeparatorClicked(it)) },
            onThousandsSeparatorClick = { onEvent(PreferencesUiEvent.ThousandsSeparatorClicked(it)) }
        )

        FilledButton(
            text = stringResource(R.string.save),
            onClick = { onEvent(PreferencesUiEvent.SaveButtonClicked) },
            modifier = Modifier.fillMaxWidth(),
            enabled = state.isSaveButtonEnabled
        )
    }
}