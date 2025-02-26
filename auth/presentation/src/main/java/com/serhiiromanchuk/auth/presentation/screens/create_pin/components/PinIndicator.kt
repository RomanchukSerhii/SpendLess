package com.serhiiromanchuk.auth.presentation.screens.create_pin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors

@Composable
internal fun PinIndicator(
    enteredPin: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(18.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            PinDot(
                enabled = index + 1 <= enteredPin.length
            )
        }
    }
}

@Composable
private fun PinDot(
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = if (enabled) {
                    MaterialTheme.colorScheme.primary
                } else {
                    AppColors.OnBackgroundOpacity12
                },
                shape = CircleShape
            )
            .size(18.dp),
    )
}