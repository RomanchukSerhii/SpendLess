package com.serhiiromanchuk.core.presentation.designsystem.components.dropdown

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppDropdown(
    items: List<DropdownItem>,
    selectedItem: DropdownItem,
    onItemSelected: (DropdownItem) -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 40.dp
) {
    var expanded by remember { mutableStateOf(false) }
    var categoryWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        CategoryCard(
            category = selectedItem,
            onClick = { expanded = !expanded },
            isExpanded = expanded,
            iconSize = iconSize,
            modifier = Modifier.onSizeChanged { size ->
                categoryWidth = with(density) { size.width.toDp() }
            }
        )

        Spacer(modifier = Modifier.height(4.dp))

        SelectDropdown(
            expanded = expanded,
            onDismissRequest = { expanded = !expanded },
            items = items,
            selectedItem = selectedItem,
            onItemSelected = {
                onItemSelected(it)
                expanded = !expanded
            },
            dropdownWidth = categoryWidth,
            iconSize = iconSize
        )
    }
}

@Composable
private fun SelectDropdown(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<DropdownItem>,
    selectedItem: DropdownItem,
    onItemSelected: (DropdownItem) -> Unit,
    dropdownWidth: Dp,
    iconSize: Dp,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        modifier = modifier
            .width(dropdownWidth)
            .heightIn(max = 240.dp),
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(16.dp),
        containerColor = Color.White,
        shadowElevation = 6.dp
    ) {
        items.forEach { categoryItem ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(categoryItem.titleRes, *categoryItem.titleArgs),
                        modifier = Modifier.offset(x = (-4).dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                onClick = { onItemSelected(categoryItem) },
                leadingIcon = {
                    categoryItem.TextIcon(
                        modifier = Modifier.size(iconSize),
                        fontSize = 18.sp
                    )
                },
                trailingIcon = {
                    if (categoryItem == selectedItem) {
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