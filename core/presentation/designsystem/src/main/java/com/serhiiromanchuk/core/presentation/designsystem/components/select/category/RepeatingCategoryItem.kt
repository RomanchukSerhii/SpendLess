package com.serhiiromanchuk.core.presentation.designsystem.components.select.category

import androidx.compose.runtime.Composable
import com.serhiiromanchuk.core.presentation.designsystem.RepeatIcon

// TODO: finalize the list according to the requirements
enum class RepeatingCategoryItem(override val title: String) : DropdownItem {
    NOT_REPEAT("Does not repeat") {
        @Composable
        override fun Icon() { RepeatIcon() }
    },
    DAILY("Daily") {
        @Composable
        override fun Icon() { RepeatIcon() }
    },
}