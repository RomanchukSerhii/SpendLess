package com.serhiiromanchuk.transactions.screens.export_transactions.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.appShadow
import com.serhiiromanchuk.transactions.presentation.R
import com.serhiiromanchuk.transactions.screens.export_transactions.handling.ExportRange

@Composable
fun ExportRangeDropdown(
    selectedItem: ExportRange,
    onItemSelected: (ExportRange) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var categoryWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.export_range),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        ExportRangeCard(
            exportRange = selectedItem,
            onClick = { expanded = !expanded },
            isExpanded = expanded,
            modifier = Modifier.onSizeChanged { size ->
                categoryWidth = with(density) { size.width.toDp() }
            }
        )

        Spacer(modifier = Modifier.height(4.dp))

        ExportDropdown(
            expanded = expanded,
            onDismissRequest = { expanded = !expanded },
            items = ExportRange.entries,
            selectedItem = selectedItem,
            onItemSelected = {
                onItemSelected(it)
                expanded = !expanded
            },
            dropdownWidth = categoryWidth,
        )
    }
}

@Composable
private fun ExportRangeCard(
    exportRange: ExportRange,
    onClick: (ExportRange) -> Unit,
    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .appShadow()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(exportRange) }
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.height(48.dp).padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(exportRange.titleRes),
                modifier = Modifier.padding(start = 12.dp).weight(1f),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Icon(
                painter = if (isExpanded) {
                    painterResource(com.serhiiromanchuk.core.presentation.designsystem.R.drawable.arrow_drop_up)
                } else {
                    painterResource(com.serhiiromanchuk.core.presentation.designsystem.R.drawable.arrow_drop_down)
                },
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
    }
}

@Composable
private fun ExportDropdown(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<ExportRange>,
    selectedItem: ExportRange,
    onItemSelected: (ExportRange) -> Unit,
    dropdownWidth: Dp,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        modifier = modifier.width(dropdownWidth),
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(16.dp),
        containerColor = Color.White,
        shadowElevation = 6.dp
    ) {
        items.forEach { exportRange ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(exportRange.titleRes),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },
                onClick = { onItemSelected(exportRange) },
                trailingIcon = {
                    if (exportRange == selectedItem) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(18.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                contentPadding = PaddingValues(horizontal = 4.dp)
            )
        }
    }
}