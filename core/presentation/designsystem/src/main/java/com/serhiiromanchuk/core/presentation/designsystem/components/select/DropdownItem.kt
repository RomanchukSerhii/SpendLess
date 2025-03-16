package com.serhiiromanchuk.core.presentation.designsystem.components.select

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit

interface DropdownItem {
    val titleRes: Int
    val titleArgs: Array<Any> get() = arrayOf()
    @Composable
    fun TextIcon(
        modifier: Modifier,
        fontSize: TextUnit
    ) {}
}