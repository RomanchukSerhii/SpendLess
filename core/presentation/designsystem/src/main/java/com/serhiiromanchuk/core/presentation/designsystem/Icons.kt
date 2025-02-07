package com.serhiiromanchuk.core.presentation.designsystem

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val ExportIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_export)

@Composable
fun HomeIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x1F3E0)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun FoodIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x1F355)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun EntertainmentIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x1F4BB)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun ClothingIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x1F454)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun HealthIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x2764)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun PersonalCareIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x1F6C1)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun TransportationIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x1F697)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun EducationIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x1F393)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun SavingInvestmentsIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x1F48E)),
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
fun OtherIcon(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = String(Character.toChars(0x2699)),
        modifier = modifier,
        fontSize = fontSize
    )
}