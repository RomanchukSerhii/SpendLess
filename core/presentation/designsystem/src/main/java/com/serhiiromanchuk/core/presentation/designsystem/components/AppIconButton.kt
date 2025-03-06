package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.SettingsIcon
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme

@Composable
private fun SpendLessIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(44.dp),
        shape = RoundedCornerShape(16.dp),
        colors = colors,
        enabled = enabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun OnPrimaryIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SpendLessIconButton(
        icon = icon,
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.12f),
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun StandardIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SpendLessIconButton(
        icon = icon,
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
fun ErrorIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SpendLessIconButton(
        icon = icon,
        contentDescription = contentDescription,
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = AppColors.ErrorOpacity08,
            contentColor = MaterialTheme.colorScheme.error
        )
    )
}

@Preview(showBackground = true)
@Composable
fun IconButtonDemo() {
    SpendLessTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StandardIconButton(
                icon = SettingsIcon,
                contentDescription = null,
                onClick = { }
            )
            ErrorIconButton(
                icon = SettingsIcon,
                contentDescription = null,
                onClick = { }
            )
            OnPrimaryIconButton(
                icon = SettingsIcon,
                contentDescription = null,
                onClick = { }
            )
        }
    }
}
