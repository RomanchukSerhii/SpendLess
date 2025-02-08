package com.serhiiromanchuk.core.presentation.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

object ExpenseIncomeColors {
    @Composable
    fun itemBackgroundColor(isExpanded: Boolean): Color {
        return if (isExpanded) AppColors.SurfContainerLowest else Color.Transparent
    }

    @Composable
    fun categoryIconBackground(isIncome: Boolean): Color {
        return if (isIncome) AppColors.SecondaryFixed else AppColors.PrimaryFixed
    }

    val notesIconBackground @Composable get() = MaterialTheme.colorScheme.surfaceContainerLowest

    @Composable
    fun amountColor(isIncome: Boolean): Color {
        return if  (isIncome) AppColors.Success else MaterialTheme.colorScheme.onSurface
    }

    @Composable
    fun notesIconTint(isExpanded: Boolean, isIncome: Boolean): Color {
        return when {
            isExpanded && isIncome -> MaterialTheme.colorScheme.secondary
            isExpanded  -> MaterialTheme.colorScheme.primaryContainer
            isIncome -> AppColors.SecondaryFixed
            else -> MaterialTheme.colorScheme.inversePrimary
        }
    }
}
data class IconButtonColorsScheme(
    val containerColor: @Composable (Boolean) -> Color,
    val contentColor: @Composable () -> Color,
    val disabledContainerColor: @Composable () -> Color,
    val disabledContentColor: @Composable () -> Color
)

object AppIconButtonColors {
    val Filled = IconButtonColorsScheme(
        containerColor = { isPressed ->
            if (isPressed) AppColors.PrimaryOpacity12 else MaterialTheme.colorScheme.primary
        },
        contentColor = { MaterialTheme.colorScheme.onPrimary },
        disabledContainerColor = { AppColors.SurfContainerHighest },
        disabledContentColor = { AppColors.OnSurfaceOpacity12 },
    )

    val Standard = IconButtonColorsScheme(
        containerColor = { Color.Transparent },
        contentColor = { MaterialTheme.colorScheme.onSurface },
        disabledContainerColor = { Color.Transparent },
        disabledContentColor = { MaterialTheme.colorScheme.onSurfaceVariant },
    )

    val Error = IconButtonColorsScheme(
        containerColor = { MaterialTheme.colorScheme.errorContainer },
        contentColor = { MaterialTheme.colorScheme.error },
        disabledContainerColor = { MaterialTheme.colorScheme.surfaceVariant },
        disabledContentColor = { MaterialTheme.colorScheme.onSurfaceVariant },
    )
    val OnPrimary = IconButtonColorsScheme(
        containerColor = { isPressed ->
            if (isPressed) AppColors.PrimaryContainerOpacity08 else AppColors.OnPrimaryContainerOpacity12
        },
        contentColor = { MaterialTheme.colorScheme.onPrimary },
        disabledContainerColor = { AppColors.SurfContainerHighest },
        disabledContentColor = { AppColors.OnSurfaceOpacity12 }
    )
}