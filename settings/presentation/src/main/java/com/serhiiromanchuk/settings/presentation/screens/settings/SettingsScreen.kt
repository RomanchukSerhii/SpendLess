package com.serhiiromanchuk.settings.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppCard
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTopBar
import com.serhiiromanchuk.core.presentation.designsystem.components.appShadow
import com.serhiiromanchuk.core.presentation.ui.ObserveAsActions
import com.serhiiromanchuk.core.presentation.ui.components.BaseContentLayout
import com.serhiiromanchuk.settings.presentation.R
import com.serhiiromanchuk.settings.presentation.screens.SettingsSharedViewModel
import com.serhiiromanchuk.settings.presentation.screens.settings.components.LogoutButton
import com.serhiiromanchuk.settings.presentation.screens.settings.components.PreferencesButton
import com.serhiiromanchuk.settings.presentation.screens.settings.components.SecurityButton
import com.serhiiromanchuk.settings.presentation.screens.settings.handling.SettingsAction
import com.serhiiromanchuk.settings.presentation.screens.settings.handling.SettingsUiEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    navigateBack: () -> Unit,
    navigateToPreferences: () -> Unit,
    navigateToSecurity: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: SettingsSharedViewModel = koinViewModel()
) {

    ObserveAsActions(viewModel.settingsActions) { action ->
        when (action) {
            SettingsAction.NavigateToLogin -> navigateToLogin()
        }
    }

    BaseContentLayout(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.settings),
                onBackClick = navigateBack
            )
        },
        onBackPressed = navigateBack
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val shadowModifier = Modifier.appShadow(
                blurRadius = 24.dp,
                positionY = 8.dp,
                shadowAlpha = 0.1f
            )

            AppCard(
                modifier = shadowModifier
            ) {
                Column {
                    PreferencesButton(onClick = navigateToPreferences)
                    SecurityButton(onClick = navigateToSecurity)
                }
            }

            AppCard(
                modifier = shadowModifier
            ) {
                LogoutButton(
                    onClick = { viewModel.onEvent(SettingsUiEvent.LogOutButtonClicked) }
                )
            }
        }
    }
}