package com.serhiiromanchuk.transactions.common_components

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
import com.serhiiromanchuk.core.presentation.designsystem.RepeatIcon
import com.serhiiromanchuk.core.presentation.designsystem.SavingIcon
import com.serhiiromanchuk.core.presentation.designsystem.TransportationIcon
import com.serhiiromanchuk.core.presentation.designsystem.components.select.DropdownItem
import com.serhiiromanchuk.transactions.domain.Expense
import com.serhiiromanchuk.transactions.presentation.R


enum class ExpenseCategory(override val titleRes: Int) : DropdownItem {
    HOME(R.string.home) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            HomeIcon(fontSize)
        }
    },
    FOOD(R.string.food_groceries) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            FoodIcon(fontSize)
        }
    },
    ENTERTAINMENT(R.string.entertainment) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            EntertainmentIcon(fontSize)
        }
    },
    CLOTHING(R.string.clothing_accessories) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            ClothingIcon(fontSize)
        }
    },
    HEALTH(R.string.health_wellness) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            HealthIcon(fontSize)
        }
    },
    PERSONAL_CARE(R.string.personal_care) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            PersonalCareIcon(fontSize)
        }
    },
    TRANSPORTATION(R.string.transportation) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            TransportationIcon(fontSize)
        }
    },
    EDUCATION(R.string.education) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            EducationIcon(fontSize)
        }
    },
    SAVING(R.string.saving_investments) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            SavingIcon(fontSize)
        }
    },
    OTHER(R.string.other) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) {
            OtherIcon(fontSize)
        }
    };

    companion object {
        fun fromExpense(expense: Expense): ExpenseCategory {
            return when (expense) {
                Expense.HOME -> HOME
                Expense.FOOD -> FOOD
                Expense.ENTERTAINMENT -> ENTERTAINMENT
                Expense.CLOTHING -> CLOTHING
                Expense.HEALTH -> HEALTH
                Expense.PERSONAL_CARE -> PERSONAL_CARE
                Expense.TRANSPORTATION -> TRANSPORTATION
                Expense.EDUCATION -> EDUCATION
                Expense.SAVING -> SAVING
                Expense.OTHER -> OTHER
            }
        }
    }
}

// TODO: finalize the list according to the requirements
enum class RepeatingCategory(override val titleRes: Int) : DropdownItem {
    NOT_REPEAT(R.string.does_not_repeat) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) { RepeatIcon(fontSize) }
    },
    DAILY(R.string.daily) {
        @Composable
        override fun TextIcon(fontSize: TextUnit) { RepeatIcon(fontSize) }
    },
}