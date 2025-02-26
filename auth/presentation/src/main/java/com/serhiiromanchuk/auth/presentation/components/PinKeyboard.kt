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
    onFingerprintClick: (() -> Unit)? = null
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
                    onClick = onNumberClick
                )
                9 -> FingerprintKey(onClick = onFingerprintClick)
                10 -> PinKey(
                    number = 0,
                    onClick = onNumberClick
                )
                else -> BackSpaceKey(onClick = onBackspaceClick)
            }
        }
    }
}

@Composable
private fun FingerprintKey(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier,
    ) {
        onClick?.let {
            SpecialKey(
                onClick = onClick
            ) {
                Icon(
                    imageVector = FingerprintIcon,
                    contentDescription = stringResource(R.string.use_biometric_authentication),
                    tint = AppColors.OnPrimaryFixed
                )
            }
        }
    }
}

@Composable
private fun BackSpaceKey(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SpecialKey(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Backspace,
            contentDescription = stringResource(R.string.delete_the_last_digit),
            tint = AppColors.OnPrimaryFixed
        )
    }
}

@Composable
private fun PinKey(
    number: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    KeyButton(
        modifier = modifier,
        onClick = { onClick(number) },
        color = AppColors.PrimaryFixed
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = AppColors.OnPrimaryFixed
        )
    }
}

@Composable
private fun SpecialKey(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    KeyButton(
        color = AppColors.PrimaryFixed.copy(alpha = 0.3f),
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