package com.serhiiromanchuk.transactions.screens.create_transaction.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.select.SelectCategory
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.common_components.RepeatingCategory

@Composable
fun TransactionOptions(
    isExpense: Boolean,
    selectedSpendCategory: ExpenseCategory,
    selectedRepeatingCategory: RepeatingCategory,
    onSpendCategoryClick: (ExpenseCategory) -> Unit,
    onRepeatingCategoryClick: (RepeatingCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Select spend category
        if (isExpense) {
            SelectCategory(
                items = ExpenseCategory.entries,
                selectedItem = selectedSpendCategory,
                onItemSelected = { onSpendCategoryClick(it as ExpenseCategory) },
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        // Select repeat category
        SelectCategory(
            items = RepeatingCategory.entries,
            selectedItem = selectedRepeatingCategory,
            onItemSelected = { onRepeatingCategoryClick(it as RepeatingCategory)}
        )
    }
}