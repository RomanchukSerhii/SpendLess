package com.serhiiromanchuk.settings.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.LockIcon
import com.serhiiromanchuk.core.presentation.designsystem.LogoutIcon
import com.serhiiromanchuk.core.presentation.designsystem.SettingsIcon
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.settings.presentation.R

@Composable
fun PreferencesButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsButton(
        icon = { RoundedIcon(SettingsIcon) },
        text = stringResource(R.string.preferences),
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun SecurityButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsButton(
        icon = { RoundedIcon(LockIcon) },
        text = stringResource(R.string.settings),
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun LogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsButton(
        icon = { LogoutRoundedIcon() },
        text = stringResource(R.string.log_out),
        onClick = onClick,
        modifier = modifier,
        textColor = MaterialTheme.colorScheme.error
    )
}

@Composable
private fun SettingsButton(
    icon: @Composable () -> Unit,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium.copy(color = textColor)
            )
        }
    }
}

@Composable
private fun RoundedIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    background: Color = AppColors.SurfContainerLow,
    iconColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Surface(
        modifier = modifier.size(40.dp),
        shape = RoundedCornerShape(12.dp),
        color = background,
        contentColor = iconColor
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun LogoutRoundedIcon(modifier: Modifier = Modifier) {
    RoundedIcon(
        modifier = modifier,
        imageVector = LogoutIcon,
        background = AppColors.ErrorOpacity08,
        iconColor = MaterialTheme.colorScheme.error
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun PreviewSettingsIcon() {
    SpendLessTheme {
        Column {
            RoundedIcon(
                modifier = Modifier.padding(8.dp),
                imageVector = SettingsIcon
            )
            LogoutRoundedIcon()
        }

    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFD9D9D9
)
@Composable
private fun PreviewSettingsButtons() {
    SpendLessTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsButton(
                text = "Preferences",
                icon = { RoundedIcon(SettingsIcon) },
                onClick = {}
            )
            SettingsButton(
                text = "Log out",
                icon = { LogoutRoundedIcon() },
                onClick = {},
                textColor = MaterialTheme.colorScheme.error
            )
        }

    }
}