package com.serhiiromanchuk.core.presentation.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
fun IncomeIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Box(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(ExpenseIncomeColors.categoryIconBackground(isIncome = true)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = String(Character.toChars(0x1F4B0)),
            fontSize = fontSize
        )
    }
}

@Composable
fun HomeIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x1F3E0)),
            fontSize = fontSize
        )
    }
}

@Composable
fun FoodIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x1F355)),
            fontSize = fontSize
        )
    }
}

@Composable
fun EntertainmentIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x1F4BB)),
            fontSize = fontSize
        )
    }
}

@Composable
fun ClothingIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x1F454)),
            fontSize = fontSize
        )
    }
}

@Composable
fun HealthIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x2764)),
            fontSize = fontSize
        )
    }
}

@Composable
fun PersonalCareIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x1F6C1)),
            fontSize = fontSize
        )
    }
}

@Composable
fun TransportationIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.offset(y = (-4).dp),
            text = String(Character.toChars(0x1F697)),
            fontSize = fontSize
        )
    }
}

@Composable
fun EducationIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x1F393)),
            fontSize = fontSize
        )
    }
}

@Composable
fun SavingIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x1F48E)),
            fontSize = fontSize
        )
    }
}

@Composable
fun OtherIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    ExpenseIconBackground(
        modifier = modifier
    ) {
        Text(
            text = String(Character.toChars(0x2699)),
            fontSize = fontSize
        )
    }
}

@Composable
fun MoneyIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    Text(
        text = String(Character.toChars(0x1F4B8)),
        fontSize = fontSize
    )
}

@Composable
fun RepeatIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    Box(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = String(Character.toChars(0x1F504)),
            fontSize = fontSize
        )
    }
}

@Composable
private fun ExpenseIconBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(ExpenseIncomeColors.categoryIconBackground(isIncome = false)),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}