package com.serhiiromanchuk.transactions.screens.dashboard

import androidx.compose.runtime.Composable
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFAB
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import com.serhiiromanchuk.transactions.screens.create_transaction.CreateTransactionViewModel
import com.serhiiromanchuk.transactions.screens.dashboard.components.CreateTransactionBottomSheet
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreenRoot(
    onSettingsClick: () -> Unit,
    dashboardViewModel: DashboardViewModel = koinViewModel(),
    createTransactionViewModel: CreateTransactionViewModel = koinViewModel()
) {
    BaseContentLayout(
        floatingActionButton = {
            AppFAB(
                onClick = {
                    dashboardViewModel.onEvent(DashboardUiEvent.CreateTransactionSheetToggled)
                }
            )
        }
    ) {
        DashboardScreen(
            state = dashboardViewModel.state,
            onEvent = dashboardViewModel::onEvent
        )
    }
    if (dashboardViewModel.state.isCreateTransactionSheetOpened) {
        CreateTransactionBottomSheet(
            viewModel = createTransactionViewModel,
            onDismissRequest = {
                dashboardViewModel.onEvent(DashboardUiEvent.CreateTransactionSheetToggled)
            }
        )
    }
}

@Composable
private fun DashboardScreen(
    state: DashboardUiState,
    onEvent: (DashboardUiEvent) -> Unit
) {

}

