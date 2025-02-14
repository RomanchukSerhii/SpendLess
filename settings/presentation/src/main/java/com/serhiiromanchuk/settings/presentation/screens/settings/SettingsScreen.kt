package com.serhiiromanchuk.settings.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppCard
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTopBar
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
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.settings),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp),
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