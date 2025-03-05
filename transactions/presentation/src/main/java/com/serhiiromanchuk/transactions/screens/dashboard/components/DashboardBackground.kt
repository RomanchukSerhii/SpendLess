package com.serhiiromanchuk.transactions.screens.dashboard.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme

@Composable
fun DashboardBackground(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthPx = with(density) {
        configuration.screenWidthDp.dp.roundToPx()
    }
    val smallDimension = minOf(
        configuration.screenWidthDp.dp,
        configuration.screenHeightDp.dp
    )
    val smallDimensionPx = with(density) {
        smallDimension.roundToPx()
    }
    val primaryColor = MaterialTheme.colorScheme.primary
    val isAtLeastAndroid12 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.OnPrimaryFixed)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if(isAtLeastAndroid12) {
                        Modifier.blur(smallDimension / 4)
                    } else Modifier
                )
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            if (isAtLeastAndroid12) primaryColor else primaryColor.copy(alpha = 0.4f),
                            AppColors.OnPrimaryFixed
                        ),
                        center = Offset(
                            x = screenWidthPx / 4f,
                            y = with(density) { 100.dp.toPx() }
                        ),
                        radius = smallDimensionPx / 2f
                    )
                )
        )
    }
}

@Preview
@Composable
private fun DashboardBackgroundPreview() {
    SpendLessTheme {
        DashboardBackground()
    }
}