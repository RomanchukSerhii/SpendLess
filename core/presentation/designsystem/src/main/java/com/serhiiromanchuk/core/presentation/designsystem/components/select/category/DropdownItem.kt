package com.serhiiromanchuk.core.presentation.designsystem.components.select.category

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit

interface DropdownItem {
    val title: String
    @Composable
    fun TextIcon(fontSize: TextUnit) {}
}