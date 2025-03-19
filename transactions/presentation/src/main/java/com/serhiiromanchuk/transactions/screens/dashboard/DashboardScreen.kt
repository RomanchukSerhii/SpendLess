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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFAB
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.components.DashboardTopBar
import com.serhiiromanchuk.core.presentation.designsystem.components.LocalSystemIconsUiController
import com.serhiiromanchuk.core.presentation.designsystem.components.SystemIconsUiController
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.transactions.screens.TransactionsSharedViewModel
import com.serhiiromanchuk.transactions.screens.create_transaction.CreateTransactionBottomSheet
import com.serhiiromanchuk.transactions.screens.dashboard.components.AccountInfo
import com.serhiiromanchuk.transactions.screens.dashboard.components.DashboardBackground
import com.serhiiromanchuk.transactions.screens.dashboard.components.LatestTransactions
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreenRoot(
    onSettingsClick: () -> Unit,
    viewModel: TransactionsSharedViewModel = koinViewModel()
) {

    DashboardScreen(
        state = viewModel.dashboardState,
        onEvent = viewModel::onEvent,
        onSettingsClick = onSettingsClick
    )

    if (viewModel.dashboardState.isCreateTransactionOpen) {
        CreateTransactionBottomSheet(
            state = viewModel.createTransactionState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
private fun DashboardScreen(
    state: DashboardUiState,
    onEvent: (DashboardUiEvent) -> Unit,
    onSettingsClick: () -> Unit,
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
                    onSettingsClick = onSettingsClick,
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
                    onClick = { onEvent(DashboardUiEvent.CreateTransactionSheetToggled) }
                )
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
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                )

                LatestTransactions(
                    modifier = Modifier.weight(1.4f),
                    latestTransactions = state.latestTransactions,
                    amountSettings = state.amountSettings
                )
            }
        }
    }
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    SpendLessTheme {
        AccountInfo(DashboardUiState.AccountInfoState())
    }
}

