package com.serhiiromanchuk.core.presentation.designsystem

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
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
fun HomeIcon() {
    Text(
        text = String(Character.toChars(0x1F3E0)),
        fontSize = 20.sp
    )
}

@Composable
fun FoodIcon() {
    Text(
        text = String(Character.toChars(0x1F355)),
        fontSize = 20.sp
    )
}

@Composable
fun EntertainmentIcon() {
    Text(
        text = String(Character.toChars(0x1F4BB)),
        fontSize = 20.sp
    )
}

@Composable
fun ClothingIcon() {
    Text(
        text = String(Character.toChars(0x1F454)),
        fontSize = 20.sp
    )
}

@Composable
fun HealthIcon() {
    Text(
        text = String(Character.toChars(0x2764)),
        fontSize = 20.sp
    )
}

@Composable
fun PersonalCareIcon() {
    Text(
        text = String(Character.toChars(0x1F6C1)),
        fontSize = 20.sp
    )
}

@Composable
fun TransportationIcon() {
    Text(
        text = String(Character.toChars(0x1F697)),
        fontSize = 20.sp
    )
}

@Composable
fun EducationIcon() {
    Text(
        text = String(Character.toChars(0x1F393)),
        fontSize = 20.sp
    )
}

@Composable
fun SavingIcon() {
    Text(
        text = String(Character.toChars(0x1F48E)),
        fontSize = 20.sp
    )
}

@Composable
fun OtherIcon() {
    Text(
        text = String(Character.toChars(0x2699)),
        fontSize = 20.sp
    )
}