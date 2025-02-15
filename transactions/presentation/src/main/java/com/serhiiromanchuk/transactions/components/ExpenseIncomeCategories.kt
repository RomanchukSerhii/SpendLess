package com.serhiiromanchuk.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.ClothingIcon
import com.serhiiromanchuk.core.presentation.designsystem.EducationIcon
import com.serhiiromanchuk.core.presentation.designsystem.EntertainmentIcon
import com.serhiiromanchuk.core.presentation.designsystem.ExpenseIncomeColors
import com.serhiiromanchuk.core.presentation.designsystem.FoodIcon
import com.serhiiromanchuk.core.presentation.designsystem.HealthIcon
import com.serhiiromanchuk.core.presentation.designsystem.HomeIcon
import com.serhiiromanchuk.core.presentation.designsystem.OtherIcon
import com.serhiiromanchuk.core.presentation.designsystem.PersonalCareIcon
import com.serhiiromanchuk.core.presentation.designsystem.R
import com.serhiiromanchuk.core.presentation.designsystem.SavingIcon
import com.serhiiromanchuk.core.presentation.designsystem.TransportationIcon

@Composable
fun ExpenseIncomeList(
    items: List<ExpenseIncomeItem>,
    modifier: Modifier = Modifier
) {
    var expandedItem by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            ExpenseIncomeCategory(
                item = item.copy(isExpanded = item.title == expandedItem),
                onExpandClick = {
                    if (!item.description.isNullOrEmpty()) {
                        expandedItem = if (expandedItem == item.title) null else item.title
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ExpenseIncomeCategory(
    item: ExpenseIncomeItem,
    onExpandClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ExpenseIncomeColors.itemBackgroundColor(item.isExpanded))
            .clickable { onExpandClick() }
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row (modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .size(44.dp)
                        .padding(end = 2.dp, bottom = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ItemIcon(item.category, item.isIncome, )
                    if (!item.description.isNullOrEmpty()) {
                        Box(
                            modifier = modifier
                                .size(20.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = 2.dp, y = 2.dp)
                        ){
                            NotesIcon(item.isExpanded, item.isIncome)
                        }
                    }
                }

                Column(modifier.weight(1f)) {
                    Text(text = item.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,)
                    ItemName(item.category.name)
                }
                Text(
                    text = item.amount,
                    color = ExpenseIncomeColors.amountColor(item.isIncome),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        if (item.isExpanded && !item.description.isNullOrEmpty()) {
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(start = 52.dp)
            )
        }
    }
}

@Composable
fun NotesIcon(
    isExpanded: Boolean,
    isIncome: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .clip(RoundedCornerShape(6.dp))
            .background(ExpenseIncomeColors.notesIconBackground)
            .size(20.dp),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(id = R.drawable.notes),
            contentDescription = "Notes",
            tint = ExpenseIncomeColors.notesIconTint(isExpanded, isIncome),
            modifier = modifier.size(14.dp)
        )
    }
}

data class ExpenseIncomeItem(
    val title: String,
    val category: SpendCategoryItem,
    val amount: String,
    val isIncome: Boolean = false,
    val description: String?,
    var isExpanded: Boolean = false
)

// Если нужна будет иконка прсто обращаешся item.Icon()
enum class SpendCategoryItem(val title: String) {
    HOME("Home") {
        @Composable
        override fun Icon() { HomeIcon() }
    },
    FOOD("Food & Groceries") {
        @Composable
        override fun Icon() { FoodIcon() }
    },
    ENTERTAINMENT("Entertainment") {
        @Composable
        override fun Icon() { EntertainmentIcon() }
    },
    CLOTHING("Entertainment") {
        @Composable
        override fun Icon() { ClothingIcon() }
    },
    HEALTH("Entertainment") {
        @Composable
        override fun Icon() { HealthIcon() }
    },
    PERSONAL_CARE("Entertainment") {
        @Composable
        override fun Icon() { PersonalCareIcon() }
    },
    TRANSPORTATION("Entertainment") {
        @Composable
        override fun Icon() { TransportationIcon() }
    },
    EDUCATION("Entertainment") {
        @Composable
        override fun Icon() { EducationIcon() }
    },
    SAVING("Entertainment") {
        @Composable
        override fun Icon() { SavingIcon() }
    },
    OTHER("Entertainment") {
        @Composable
        override fun Icon() { OtherIcon() }
    };

    @Composable
    abstract fun Icon()
}
@Preview(showBackground = true)
@Composable
fun PreviewExpenseIncomeList() {
    val sampleItems = listOf(
        ExpenseIncomeItem(
            title = "Salary",
            category = SpendCategoryItem.SAVING,
            amount = "$3000",
            isIncome = true,
            description = "Monthly salary from work",
            isExpanded = true
        ),
        ExpenseIncomeItem(
            title = "Groceries",
            category = SpendCategoryItem.FOOD,
            amount = "$250",
            isIncome = false,
            description = "Weekly grocery shopping",
            isExpanded = true
        ),
        ExpenseIncomeItem(
            title = "Transport",
            category = SpendCategoryItem.TRANSPORTATION,
            amount = "$50",
            isIncome = false,
            description = "Public transport expenses",
            isExpanded = false
        )
    )

    ExpenseIncomeList(items = sampleItems)
}
