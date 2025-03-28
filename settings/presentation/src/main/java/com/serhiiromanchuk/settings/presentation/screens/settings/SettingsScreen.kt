package com.serhiiromanchuk.settings.presentation.screens.settings

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppCard
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTopBar
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
            val shadowModifier = Modifier
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val shadowColor = Color(0xFF180040).copy(alpha = 0.1f)
                        val paint = Paint().apply {
                            isAntiAlias = true
                            val blurRadius = 24.dp.toPx()
                            asFrameworkPaint().apply {
                                color = shadowColor.toArgb()
                                maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
                            }
                        }

                        val spread = 4.dp.toPx()

                        canvas.drawRoundRect(
                            left = -spread,
                            top = 8.dp.toPx() - spread,
                            right = size.width + spread,
                            bottom = size.height + 8.dp.toPx() + spread,
                            radiusX = 16.dp.toPx(),
                            radiusY = 16.dp.toPx(),
                            paint = paint
                        )
                    }
                }

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