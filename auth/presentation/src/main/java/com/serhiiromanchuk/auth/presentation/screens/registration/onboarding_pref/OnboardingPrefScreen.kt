package com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.components.BackButton
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.components.OnboardingPrefHeader
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling.OnboardingPrefUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling.OnboardingPrefUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.components.FilledButton
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyFormatSettings
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingPrefScreenRoot(
    navigateBack: () -> Unit,
    viewModel: OnboardingPrefViewModel = koinViewModel()
) {
    OnboardingPrefScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onBackClick = navigateBack
    )
}

@Composable
private fun OnboardingPrefScreen(
    state: OnboardingPrefUiState,
    onEvent: (OnboardingPrefUiEvent) -> Unit,
    onBackClick: () -> Unit
) {
    BaseContentLayout {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BackButton(onClick = onBackClick)

            OnboardingPrefHeader(modifier = Modifier.padding(top = 12.dp))

            CurrencyFormatSettings(
                currencyFormatState = state.currencyFormatState,
                onExpensesFormatClick = { onEvent(OnboardingPrefUiEvent.ExpensesFormatClicked(it)) },
                onCurrencyClick = { onEvent(OnboardingPrefUiEvent.CurrencyClicked(it)) },
                onDecimalSeparatorClick = { onEvent(OnboardingPrefUiEvent.DecimalSeparatorClicked(it)) },
                onThousandsSeparatorClick = { onEvent(OnboardingPrefUiEvent.ThousandsSeparatorClicked(it)) },
                modifier = Modifier.padding(vertical = 24.dp)
            )

            FilledButton(
                text = stringResource(R.string.start_tracking),
                onClick = { onEvent(OnboardingPrefUiEvent.StartButtonClicked) },
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 24.dp),
                enabled = state.isStartButtonEnabled
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingPrefScreenPreview() {
    SpendLessTheme {
        OnboardingPrefScreen(
            state = OnboardingPrefUiState(),
            onEvent = {},
            onBackClick = {}
        )
    }
}