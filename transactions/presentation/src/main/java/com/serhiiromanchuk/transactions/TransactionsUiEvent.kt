package com.serhiiromanchuk.transactions

import com.serhiiromanchuk.transactions.components.SpendCategoryItem


sealed interface TransactionsUiEvent {
    data object ActionButtonClicked: TransactionsUiEvent
//    data class CategorySelected(val category: SpendCategoryItem) : TransactionsUiEvent
}