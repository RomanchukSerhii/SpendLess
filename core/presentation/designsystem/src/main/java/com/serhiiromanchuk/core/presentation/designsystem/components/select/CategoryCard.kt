package com.serhiiromanchuk.core.presentation.designsystem.components.select

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.R
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.DropdownItem
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.RepeatingCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.SpendCategoryItem

@Composable
fun CategoryCard(
    category: DropdownItem,
    onClick: (DropdownItem) -> Unit,
    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { onClick(category) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            category.Icon()

            Text(
                text = category.title,
                modifier = Modifier.weight(1f).padding(start = 4.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Icon(
                painter = if (isExpanded) {
                    painterResource(R.drawable.arrow_drop_up)
                } else {
                    painterResource(R.drawable.arrow_drop_down)
                },
                contentDescription = "Expand",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
    }
}

@Preview(
    apiLevel = 34,
    showBackground = true,
    backgroundColor = 0xFFF6F3F3
)
@Composable
fun PreviewSpendCategoryCard() {
    MaterialTheme {
        CategoryCard(
            category = SpendCategoryItem.FOOD,
            onClick = {},
            isExpanded = true,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    apiLevel = 34,
    showBackground = true,
    backgroundColor = 0xFFF6F3F3
)
@Composable
fun PreviewRepeatingCategoryCard() {
    MaterialTheme {
        CategoryCard(
            category = RepeatingCategoryItem.NOT_REPEAT,
            onClick = {},
            isExpanded = false,
            modifier = Modifier.padding(16.dp)
        )
    }
}