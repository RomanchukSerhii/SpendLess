package com.serhiiromanchuk.settings.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppCard
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTopBar
import com.serhiiromanchuk.core.presentation.ui.components.BaseContentLayout
import com.serhiiromanchuk.settings.presentation.R
import com.serhiiromanchuk.settings.presentation.screens.settings.components.LogoutButton
import com.serhiiromanchuk.settings.presentation.screens.settings.components.PreferencesButton
import com.serhiiromanchuk.settings.presentation.screens.settings.components.SecurityButton

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onPreferencesClick: () -> Unit,
    onSecurityClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    BaseContentLayout(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.settings),
                onBackClick = onBackClick
            )
        },
        onBackPressed = onBackClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppCard {
                Column {
                    PreferencesButton(onClick = onPreferencesClick)
                    SecurityButton(onClick = onSecurityClick)
                }
            }

            AppCard {
                LogoutButton(onClick = onLogoutClick)
            }
        }
    }
}