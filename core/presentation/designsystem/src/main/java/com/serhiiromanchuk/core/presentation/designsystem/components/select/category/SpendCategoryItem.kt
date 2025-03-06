package com.serhiiromanchuk.core.presentation.designsystem.components.select.category

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import com.serhiiromanchuk.core.presentation.designsystem.ClothingIcon
import com.serhiiromanchuk.core.presentation.designsystem.EducationIcon
import com.serhiiromanchuk.core.presentation.designsystem.EntertainmentIcon
import com.serhiiromanchuk.core.presentation.designsystem.FoodIcon
import com.serhiiromanchuk.core.presentation.designsystem.HealthIcon
import com.serhiiromanchuk.core.presentation.designsystem.HomeIcon
import com.serhiiromanchuk.core.presentation.designsystem.OtherIcon
import com.serhiiromanchuk.core.presentation.designsystem.PersonalCareIcon
import com.serhiiromanchuk.core.presentation.designsystem.SavingIcon
import com.serhiiromanchuk.core.presentation.designsystem.TransportationIcon

enum class SpendCategoryItem(override val title: String) : DropdownItem {
    HOME("Home") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            HomeIcon(fontSize)
        }
    },
    FOOD("Food & Groceries") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            FoodIcon(fontSize)
        }
    },
    ENTERTAINMENT("Entertainment") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            EntertainmentIcon(fontSize)
        }
    },
    CLOTHING("Clothing & Accessories") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            ClothingIcon(fontSize)
        }
    },
    HEALTH("Health & Wellness") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            HealthIcon(fontSize)
        }
    },
    PERSONAL_CARE("Personal Care") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            PersonalCareIcon(fontSize)
        }
    },
    TRANSPORTATION("Transportation") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            TransportationIcon(fontSize)
        }
    },
    EDUCATION("Education") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            EducationIcon(fontSize)
        }
    },
    SAVING("Saving & Investments") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            SavingIcon(fontSize)
        }
    },
    OTHER("Other") {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            OtherIcon(fontSize)
        }
    };
}