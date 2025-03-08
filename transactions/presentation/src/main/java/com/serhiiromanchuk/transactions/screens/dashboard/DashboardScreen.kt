package com.serhiiromanchuk.transactions.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFAB
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.components.DashboardTopBar
import com.serhiiromanchuk.core.presentation.designsystem.components.LocalSystemIconsUiController
import com.serhiiromanchuk.core.presentation.designsystem.components.SystemIconsUiController
import com.serhiiromanchuk.transactions.screens.create_transaction.CreateTransactionViewModel
import com.serhiiromanchuk.transactions.screens.dashboard.components.AccountInfo
import com.serhiiromanchuk.transactions.screens.dashboard.components.CreateTransactionBottomSheet
import com.serhiiromanchuk.transactions.screens.dashboard.components.DashboardBackground
import com.serhiiromanchuk.transactions.screens.dashboard.components.LatestTransactions
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreenRoot(
    onSettingsClick: () -> Unit,
    dashboardViewModel: DashboardViewModel = koinViewModel(),
    createTransactionViewModel: CreateTransactionViewModel = koinViewModel()
) {
    DashboardScreen(
        state = dashboardViewModel.state,
        onEvent = dashboardViewModel::onEvent,
        onFabClick = { dashboardViewModel.onEvent(DashboardUiEvent.CreateTransactionSheetToggled) }
    )

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
    onEvent: (DashboardUiEvent) -> Unit,
    onFabClick: () -> Unit
) {
    CompositionLocalProvider(
        LocalSystemIconsUiController provides SystemIconsUiController(
            isStatusBarIconsDark = false
        )
    ) {
        BaseContentLayout(
            topBar = {
                DashboardTopBar(
                    name = "rockefeller74",
                    onSettingsClick = {},
                    onExportClick = {}
                )
            },
            floatingActionButton = {
                AppFAB(modifier = Modifier
                    .padding(
                        bottom = LocalDensity.current.run {
                            WindowInsets.navigationBars.getBottom(this).toDp()
                        }
                    ),
                    onClick = onFabClick)
            },
            background = { DashboardBackground() },
            contentWindowInsets = WindowInsets.statusBars,
            horizontalPadding = Dp.Unspecified
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AccountInfo(
                    accountInfoState = state.accountInfoState,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                LatestTransactions(
                    latestTransactions = state.transactions
                )
            }
        }
    }

}

