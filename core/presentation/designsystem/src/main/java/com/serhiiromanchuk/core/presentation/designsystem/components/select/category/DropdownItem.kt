package com.serhiiromanchuk.core.presentation.designsystem.components.select.category

import androidx.compose.runtime.Composable

interface DropdownItem {
    val title: String
    @Composable
    fun Icon() {}
}