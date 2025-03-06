package com.serhiiromanchuk.core.presentation.designsystem.components.select.category

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import com.serhiiromanchuk.core.presentation.designsystem.RepeatIcon

// TODO: finalize the list according to the requirements
enum class RepeatingCategoryItem(override val title: String) : DropdownItem {
    NOT_REPEAT("Does not repeat") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) { RepeatIcon(fontSize) }
    },
    DAILY("Daily") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) { RepeatIcon(fontSize) }
    },
}