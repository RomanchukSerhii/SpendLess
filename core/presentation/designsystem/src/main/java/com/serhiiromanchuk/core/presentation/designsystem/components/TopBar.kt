package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.R
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    name: String,
    onSettingsClick: () -> Unit,
    onExportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(name, color = MaterialTheme.colorScheme.onPrimary) },
        actions = {
            Row (modifier = modifier.padding(end = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)){
                OnPrimaryIconButton(
                    onClick = { onExportClick() },
                    iconResId = R.drawable.download
                )
                OnPrimaryIconButton(
                    onClick = { onSettingsClick() },
                )
            }
        },
        colors = topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}
@Preview(showBackground = true,
    backgroundColor = 0xFF25004D)
@Composable
fun TopBarPreview() {
    SpendLessTheme {
        TopBar(
            name = "My App",
            onSettingsClick = {},
            onExportClick = {}
        )
    }
}