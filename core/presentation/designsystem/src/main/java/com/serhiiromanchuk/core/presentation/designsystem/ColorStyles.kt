package com.serhiiromanchuk.core.presentation.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors

object ExpenseIncomeColors {
    @Composable
    fun itemBackgroundColor(isExpanded: Boolean): Color {
        return if (isExpanded) AppColors.SurfContainerLowest else Color.Transparent
    }

    @Composable
    fun categoryIconBackground(isIncome: Boolean): Color {
        return if (isIncome) AppColors.SecondaryFixed.copy(alpha = 0.4f) else AppColors.PrimaryFixed
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