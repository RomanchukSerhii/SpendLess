package com.serhiiromanchuk.transactions.common_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.serhiiromanchuk.core.presentation.ui.InstantFormatter
import com.serhiiromanchuk.transactions.presentation.R


enum class ExpenseCategory(
    override val titleRes: Int
) : DropdownItem {
    HOME(R.string.home) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            HomeIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    FOOD(R.string.food_groceries) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            FoodIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    ENTERTAINMENT(R.string.entertainment) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            EntertainmentIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    CLOTHING(R.string.clothing_accessories) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            ClothingIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    HEALTH(R.string.health_wellness) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            HealthIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    PERSONAL_CARE(R.string.personal_care) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            PersonalCareIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    TRANSPORTATION(R.string.transportation) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            TransportationIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    EDUCATION(R.string.education) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            EducationIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    SAVING(R.string.saving_investments) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            SavingIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    OTHER(R.string.other) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            OtherIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    }
}

enum class RepeatingCategory(override val titleRes: Int, override val titleArgs: Array<Any>) :
    DropdownItem {
    NOT_REPEAT(
        titleRes = R.string.does_not_repeat,
        titleArgs = emptyArray()
    ) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            RepeatIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    DAILY(
        titleRes = R.string.daily,
        titleArgs = emptyArray()
    ) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            RepeatIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    WEEKLY(
        titleRes = R.string.weekly,
        titleArgs = arrayOf(InstantFormatter.getCurrentDayOfWeek())
    ) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            RepeatIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    MONTHLY(
        titleRes = R.string.monthly,
        titleArgs = arrayOf(InstantFormatter.getCurrentDayOfMonth())
    ) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            RepeatIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    },
    YEARLY(
        titleRes = R.string.yearly,
        titleArgs = arrayOf(InstantFormatter.getCurrentMonthAndDay())
    ) {
        @Composable
        override fun TextIcon(
            modifier: Modifier,
            fontSize: TextUnit
        ) {
            RepeatIcon(
                modifier = modifier,
                fontSize = fontSize
            )
        }
    }
}