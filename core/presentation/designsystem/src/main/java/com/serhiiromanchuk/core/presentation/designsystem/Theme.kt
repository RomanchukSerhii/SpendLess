package com.serhiiromanchuk.core.presentation.designsystem

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView

val LightColorScheme = lightColorScheme(
    primary = SpendLessPurpleDark,
    onPrimary = SpendLessWhite,
    primaryContainer = SpendLessPurpleBright,
    inversePrimary = SpendLessLavender,
    secondary = SpendLessOliveDark,
    secondaryContainer = SpendLessLimeGreen,
    onSecondaryContainer = SpendLessOliveDarker,
    tertiaryContainer = SpendLessLightBlue,
    background = SpendLessWhitePinkish,
    onBackground = SpendLessBlackPurple,
    surface = SpendLessWhiteSoft,
    onSurface = SpendLessBlackSoft,
    onSurfaceVariant = SpendLessGrayDark,
    outline = SpendLessGrayMedium,
    inverseSurface = SpendLessGrayDarker,
    inverseOnSurface = SpendLessWhiteLight,
    error = SpendLessRedDark,
    onError = SpendLessWhite,
)

@Composable
fun SpendLessTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}