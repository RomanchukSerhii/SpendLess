@file:OptIn(ExperimentalMaterial3Api::class)

package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.DownloadIcon
import com.serhiiromanchuk.core.presentation.designsystem.R
import com.serhiiromanchuk.core.presentation.designsystem.SettingsIcon
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    onExportClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.go_back)
                    )
                }
            }
        },
        actions = {
            if (onExportClick != null) {
                IconButton(
                    onClick = onExportClick
                ) {
                    Icon(
                        imageVector = DownloadIcon,
                        contentDescription = stringResource(R.string.go_back)
                    )
                }
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun DashboardTopBar(
    name: String,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
    onExportClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(name, color = MaterialTheme.colorScheme.onPrimary) },
        actions = {
            Row(
                modifier = modifier.padding(end = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                onExportClick?.let {
                    OnPrimaryIconButton(
                        icon = DownloadIcon,
                        contentDescription = null,
                        onClick = { onExportClick() },
                    )
                }

                OnPrimaryIconButton(
                    icon = SettingsIcon,
                    contentDescription = null,
                    onClick = { onSettingsClick() },
                )
            }
        },
        colors = topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF25004D
)
@Composable
fun TopBarPreview() {
    SpendLessTheme {
        DashboardTopBar(
            name = "My App",
            onSettingsClick = {},
            onExportClick = {}
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF25004D
)
@Composable
private fun PreviewAppTopBar() {
    SpendLessTheme {
        AppTopBar(
            title = "All Transactions",
            onBackClick = {},
            onExportClick = {}
        )
    }
}