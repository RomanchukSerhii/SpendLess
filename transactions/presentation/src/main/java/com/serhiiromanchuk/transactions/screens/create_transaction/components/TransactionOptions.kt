package com.serhiiromanchuk.transactions.screens.create_transaction.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.select.SelectCategory
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.RepeatingCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.SpendCategoryItem

@Composable
fun TransactionOptions(
    isExpense: Boolean,
    selectedSpendCategory: SpendCategoryItem,
    selectedRepeatingCategory: RepeatingCategoryItem,
    onSpendCategoryClick: (SpendCategoryItem) -> Unit,
    onRepeatingCategoryClick: (RepeatingCategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Select spend category
        if (isExpense) {
            SelectCategory(
                items = SpendCategoryItem.entries,
                selectedItem = selectedSpendCategory,
                onItemSelected = { onSpendCategoryClick(it as SpendCategoryItem) },
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        // Select repeat category
        SelectCategory(
            items = RepeatingCategoryItem.entries,
            selectedItem = selectedRepeatingCategory,
            onItemSelected = { onRepeatingCategoryClick(it as RepeatingCategoryItem)}
        )
    }
}