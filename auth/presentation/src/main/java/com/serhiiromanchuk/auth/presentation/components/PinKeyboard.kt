package com.serhiiromanchuk.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.core.presentation.designsystem.FingerprintIcon
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors

@Composable
internal fun PinKeyboard(
    onNumberClick: (Int) -> Unit,
    onBackspaceClick: () -> Unit,
    modifier: Modifier = Modifier,
    onFingerprintClick: (() -> Unit)? = null,
    enabled: Boolean = true
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(12) { index ->
            when (index) {
                in 0..8 -> PinKey(
                    number = index + 1,
                    onClick = onNumberClick,
                    enabled = enabled
                )

                9 -> FingerprintKey(
                    onClick = onFingerprintClick,
                    enabled = enabled
                )
                10 -> PinKey(
                    number = 0,
                    onClick = onNumberClick,
                    enabled = enabled
                )

                else -> BackSpaceKey(
                    onClick = onBackspaceClick,
                    enabled = enabled
                )
            }
        }
    }
}

@Composable
private fun FingerprintKey(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier,
    ) {
        onClick?.let {
            SpecialKey(
                onClick = onClick,
                enabled = enabled
            ) {
                Icon(
                    imageVector = FingerprintIcon,
                    contentDescription = stringResource(R.string.use_biometric_authentication),
                    tint = if (enabled) AppColors.OnPrimaryFixed else AppColors.OnPrimaryFixed.copy(0.3f)
                )
            }
        }
    }
}

@Composable
private fun BackSpaceKey(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    SpecialKey(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Backspace,
            contentDescription = stringResource(R.string.delete_the_last_digit),
            tint = if (enabled) AppColors.OnPrimaryFixed else AppColors.OnPrimaryFixed.copy(0.3f)
        )
    }
}

@Composable
private fun PinKey(
    number: Int,
    onClick: (Int) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    KeyButton(
        modifier = modifier,
        onClick = { onClick(number) },
        color = if (enabled) AppColors.PrimaryFixed else AppColors.PrimaryFixed.copy(alpha = 0.3f)
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = if (enabled) AppColors.OnPrimaryFixed else AppColors.OnPrimaryFixed.copy(alpha = 0.3f)
        )
    }
}

@Composable
private fun SpecialKey(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    icon: @Composable () -> Unit
) {
    KeyButton(
        color = if (enabled) {
            AppColors.PrimaryFixed.copy(alpha = 0.3f)
        } else {
            AppColors.PrimaryFixed.copy(alpha = 0.1f)
        }            ,
        onClick = onClick,
        modifier = modifier
    ) {
        icon()
    }
}

@Composable
private fun KeyButton(
    onClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val boxShape = RoundedCornerShape(32.dp)
    Box(
        modifier = modifier
            .clip(boxShape)
            .background(
                color = color,
                shape = boxShape
            )
            .aspectRatio(1f)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}