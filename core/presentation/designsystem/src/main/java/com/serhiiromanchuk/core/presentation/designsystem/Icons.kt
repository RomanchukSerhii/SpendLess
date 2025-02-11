package com.serhiiromanchuk.core.presentation.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors

val DownloadIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.download)

val BackspaceIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.backspace)

val FingerprintIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.fingerprint)

val LockIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.lock)

val LogoutIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.logout)

val NotesIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.notes)

val SettingsIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.settings)

val TodayIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.today)

val TrendingDownIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.trending_down)

val TrendingUpIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.trending_up)

val WalletIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.wallet)

@Composable
fun HomeIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x1F3E0)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun FoodIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x1F355)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun EntertainmentIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x1F4BB)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun ClothingIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x1F454)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun HealthIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x2764)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun PersonalCareIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x1F6C1)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun TransportationIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x1F697)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun EducationIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x1F393)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun SavingIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x1F48E)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun OtherIcon() {
    ExpenseIconBackground {
        Text(
            text = String(Character.toChars(0x2699)),
            fontSize = 20.sp
        )
    }
}

@Composable
fun RepeatIcon() {
    Box(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = String(Character.toChars(0x1F504)),
            fontSize = 18.sp
        )
    }
}

@Composable
private fun ExpenseIconBackground(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(ExpenseIncomeColors.categoryIconBackground(isIncome = false))
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}