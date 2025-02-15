package com.serhiiromanchuk.transactions.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.transactions.TransactionsUiEvent
import com.serhiiromanchuk.transactions.TransactionsUiState
import com.serhiiromanchuk.transactions.components.SpendCategoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class TransactionsViewModel : ViewModel() {

    private val _state = MutableStateFlow(TransactionsUiState())
    val state: StateFlow<TransactionsUiState> = _state

    fun onEvent(event: TransactionsUiEvent) {
        when (event) {
            TransactionsUiEvent.ActionButtonClicked -> transactionsSheetToggled()
//            is TransactionsUiEvent.CategorySelected -> updateSelectedCategory(event.category)
//            is TransactionsUiEvent.RepeatFrequencyChanged -> updateRepeatFrequency(event.frequency)
        }
    }

    fun transactionsSheetToggled() {
        _state.value = _state.value.copy(
            isTransactionSheetOpen = !_state.value.isTransactionSheetOpen
        )
    }

//    private fun updateSelectedCategory(category: SpendCategoryItem) {
//        _state.value = _state.value.copy(selectedCategory = category)
//    }
//
//    private fun updateRepeatFrequency(frequency: RepeatFrequency) {
//        _state.value = _state.value.copy(repeatFrequency = frequency)
}
