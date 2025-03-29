package com.serhiiromanchuk.core.presentation.designsystem.components.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serhiiromanchuk.core.presentation.designsystem.R
import com.serhiiromanchuk.core.presentation.designsystem.components.appShadow

@Composable
fun CategoryCard(
    category: DropdownItem,
    onClick: (DropdownItem) -> Unit,
    isExpanded: Boolean,
    iconSize: Dp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .appShadow()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(category) }
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.height(48.dp).padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            category.TextIcon(
                modifier = Modifier.size(iconSize),
                fontSize = 18.sp
            )

            Text(
                text = stringResource(category.titleRes, *category.titleArgs),
                modifier = Modifier.weight(1f),
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