package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.ExpenseIncomeColors

// Старайся добавлять модифаер по умолчанию в компоненты, чтобы можно было удобнее настраивать
// элементы снаружи. И еще я добавил Card чтобы сделать скругленные углы, потому что скорей всего
// элемент будет кликабельным и при клике эти углы будут видны.
@Composable
fun CategoryCard(
    category: SpendCategoryItem,
    isIncome: Boolean,
    modifier: Modifier = Modifier,
    iconSize: Dp = 40.dp
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryIcon(category, isIncome, iconSize)
            Column(Modifier.weight(1f)) {
                CategoryName(
                    category.title,
                    MaterialTheme.typography.labelMedium,
                    MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }

}

@Composable
fun CategoryIcon(
    item: SpendCategoryItem,
    isIncome: Boolean,
    backgroundSize: Dp = 40.dp,
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(ExpenseIncomeColors.categoryIconBackground(isIncome))
            .size(backgroundSize),
        contentAlignment = Alignment.Center
    ) {
        item.Icon()
    }
}

@Composable
fun CategoryName(
    name: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Text(
        text = name,
        style = textStyle,
        color = color
    )
}

@Preview(
    apiLevel = 34,
    showBackground = true,
    backgroundColor = 0xFFB5B0B9
)
@Composable
fun PreviewCategoryCard() {
    MaterialTheme {
        CategoryCard(
            category = SpendCategoryItem.FOOD,
            isIncome = false,
        )
    }
}