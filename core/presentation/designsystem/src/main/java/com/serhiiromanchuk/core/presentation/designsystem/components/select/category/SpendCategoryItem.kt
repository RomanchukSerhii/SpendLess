package com.serhiiromanchuk.core.presentation.designsystem.components.select.category

import androidx.compose.runtime.Composable
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
        override fun Icon() { HomeIcon() }
    },
    FOOD("Food & Groceries") {
        @Composable
        override fun Icon() { FoodIcon() }
    },
    ENTERTAINMENT("Entertainment") {
        @Composable
        override fun Icon() { EntertainmentIcon() }
    },
    CLOTHING("Clothing & Accessories") {
        @Composable
        override fun Icon() { ClothingIcon() }
    },
    HEALTH("Health & Wellness") {
        @Composable
        override fun Icon() { HealthIcon() }
    },
    PERSONAL_CARE("Personal Care") {
        @Composable
        override fun Icon() { PersonalCareIcon() }
    },
    TRANSPORTATION("Transportation") {
        @Composable
        override fun Icon() { TransportationIcon() }
    },
    EDUCATION("Education") {
        @Composable
        override fun Icon() { EducationIcon() }
    },
    SAVING("Saving & Investments") {
        @Composable
        override fun Icon() { SavingIcon() }
    },
    OTHER("Other") {
        @Composable
        override fun Icon() { OtherIcon() }
    };
}