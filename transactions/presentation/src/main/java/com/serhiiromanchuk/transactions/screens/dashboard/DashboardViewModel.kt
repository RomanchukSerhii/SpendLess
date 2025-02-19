package com.serhiiromanchuk.transactions.screens.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent.*
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState

class DashboardViewModel : ViewModel() {

    var state by mutableStateOf(DashboardUiState())
        private set

    fun onEvent(event: DashboardUiEvent) {
        when (event) {
            CreateTransactionSheetToggled -> toggleCreateTransactionSheet()
        }
    }

    private fun toggleCreateTransactionSheet() {
        state = state.copy(
            isCreateTransactionSheetOpened = !state.isCreateTransactionSheetOpened
        )
    }
}