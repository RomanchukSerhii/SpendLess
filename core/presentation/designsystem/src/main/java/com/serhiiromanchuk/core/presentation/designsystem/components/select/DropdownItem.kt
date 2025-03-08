package com.serhiiromanchuk.core.presentation.designsystem.components.select

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit

interface DropdownItem {
    val titleRes: Int
    @Composable
    fun TextIcon(fontSize: TextUnit) {}
}