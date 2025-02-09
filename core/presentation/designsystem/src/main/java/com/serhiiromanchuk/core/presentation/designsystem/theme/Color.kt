package com.serhiiromanchuk.core.presentation.designsystem.theme

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val SpendLessPurpleDark = Color(0xFF5A00C8)
val SpendLessWhite = Color(0xFFFFFFFF)
val SpendLessOliveDark = Color(0xFF5F6200)
val SpendLessLightBlue = Color(0xFFC4E0F9)
val SpendLessRedDark = Color(0xFFA40019)
val SpendLessPurpleBright = Color(0xFF8138FF)
val SpendLessLimeGreen = Color(0xFFD2E750)
val SpendLessOliveDarker = Color(0xFF414300)
val SpendLessPurpleDeep = Color(0xFF5900C7)
val SpendLessLavender = Color(0xFFD2BCFF)
val SpendLessWhiteSoft = Color(0xFFFCF9F9)
val SpendLessBlackSoft = Color(0xFF1B1B1C)
val SpendLessGrayDark = Color(0xFF44474B)
val SpendLessGrayMedium = Color(0xFF75777B)
val SpendLessGrayDarker = Color(0xFF303031)
val SpendLessWhiteLight = Color(0xFFF3F0F0)
val SpendLessWhitePinkish = Color(0xFFFEF7FF)
val SpendLessBlackPurple = Color(0xFF1D1A25)
val SpendLessGreenBright = Color(0xFF29AC08)
val SpendLessPurplePastel = Color(0xFFEADDFF)
val SpendLessYellowBright = Color(0xFFE5EA58)
val SpendLessYellowMuted = Color(0xFFC9CE3E)
val SpendLessPurpleDeepest = Color(0xFF24005A)
val SpendLessWhiteGrayish = Color(0xFFF6F3F3)
val SpendLessWhiteWarm = Color(0xFFF0EDED)
val SpendLessWhiteNeutral = Color(0xFFE4E2E2)

object AppColors {
    val Success = SpendLessGreenBright
    val PrimaryFixed = SpendLessPurplePastel
    val SecondaryFixed = SpendLessYellowBright
    val SecondaryFixedDim = SpendLessYellowMuted
    val OnPrimaryFixed = SpendLessPurpleDeepest
    val OnPrimaryFixedVariant = SpendLessPurpleDeep
    val SurfContainerLowest = SpendLessWhite
    val SurfContainerLow = SpendLessWhiteGrayish
    val SurfContainer = SpendLessWhiteWarm
    val SurfContainerHighest = SpendLessWhiteNeutral
    val PrimaryOpacity12 = SpendLessPurpleDark.copy(alpha = 0.12f)
    val PrimaryOpacity8 = SpendLessPurpleDark.copy(alpha = 0.8f)
    val OnPrimaryOpacity12 = SpendLessWhite.copy(alpha = 0.12f)
    val PrimaryContainerOpacity08 = SpendLessPurpleBright.copy(alpha = 0.08f)
    val OnPrimaryContainerOpacity12 = SpendLessWhite.copy(alpha = 0.12f)
    val OnSecondaryContainerOpacity08 = SpendLessOliveDarker.copy(alpha = 0.08f)
    val OnSecondaryContainerOpacity12 = SpendLessOliveDarker.copy(alpha = 0.12f)
    val ErrorOpacity08 = SpendLessRedDark.copy(alpha = 0.08f)
    val ErrorOpacity12 = SpendLessRedDark.copy(alpha = 0.12f)
    val OnBackgroundOpacity08 = SpendLessBlackPurple.copy(alpha = 0.08f)
    val OnBackgroundOpacity12 = SpendLessBlackPurple.copy(alpha = 0.12f)
    val OnSurfaceOpacity12 = SpendLessBlackSoft.copy(alpha = 0.12f)
    val OnSurfaceVariantOpacity12 = SpendLessGrayDark.copy(alpha = 0.12f)
}

object GradientScheme {
    val DashboardGradient = Brush.radialGradient(
        colors = listOf(
            Color(0xFF5A00C8),
            Color(0xFF25004D)
        ),
        center = Offset(0f, 0f),
        radius = 500f
    )
}
object ButtonColors {
    @Composable
    fun filled(): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    @Composable
    fun textBtn(pressed: Boolean = false): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = if (pressed) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f) else Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}