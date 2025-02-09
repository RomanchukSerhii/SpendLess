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
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.AppIconButtonColors
import com.serhiiromanchuk.core.presentation.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    name: String,
    onSettingsClick: () -> Unit,
    onExportClick: () -> Unit
) {
    TopAppBar(
        title = { Text(name, color = MaterialTheme.colorScheme.onPrimary) },
        actions = {
            Row (modifier = Modifier.padding(end = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)){
                IconButtonVariant(
                    contentDescription = "OnPrimary",
                    onClick = { onExportClick() },
                    colors = AppIconButtonColors.OnPrimary,
                    iconResId = R.drawable.download
                )
                IconButtonVariant(
                    contentDescription = "OnPrimary",
                    onClick = { onSettingsClick() },
                    colors = AppIconButtonColors.OnPrimary
                )
            }
        },
        colors = topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}
