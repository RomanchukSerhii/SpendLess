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
import com.serhiiromanchuk.core.domain.entity.Expense
import com.serhiiromanchuk.core.domain.entity.Income
import com.serhiiromanchuk.core.domain.entity.RepeatType
import com.serhiiromanchuk.core.domain.entity.Transaction
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
import java.time.Instant

@Composable
fun DashboardScreenRoot(
    onSettingsClick: () -> Unit,
    onShowAllClick: () -> Unit,
    viewModel: TransactionsSharedViewModel = koinViewModel()
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
                AppFAB(
                    onClick = { viewModel.onEvent(DashboardUiEvent.CreateTransactionSheetToggled) },
                    modifier = Modifier.padding(
                        bottom = LocalDensity.current.run {
                            WindowInsets.navigationBars.getBottom(this).toDp()
                        }
                    )
                )
            },
            background = { DashboardBackground() },
            contentWindowInsets = WindowInsets.statusBars,
            horizontalPadding = Dp.Unspecified
        ) {
            DashboardScreen(
                state = viewModel.dashboardState,
                onShowAllClick = onShowAllClick
            )
        }
    }


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
    onShowAllClick: () -> Unit
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
            amountSettings = state.amountSettings,
            onShowAllClick = onShowAllClick
        )
    }
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    SpendLessTheme {
        DashboardScreen(
            state = DashboardUiState(
                latestTransactions = createTestTransactions()
            ),
            onShowAllClick = {}
        )
    }
}

private fun createTestTransactions(): Map<Instant, List<Transaction>> {
    val today = Instant.now()
    val yesterday = today.minusSeconds(24 * 60 * 60) // Вчора

    val transactions = listOf(
        Transaction(
            id = 0,
            userId = 0L,
            title = "Salary",
            amount = 1000f,
            transactionType = Income,
            repeatType = RepeatType.NOT_REPEAT,
            note = "Enjoyed a coffee and a snack at Starbucks with Rick and M.",
            transactionDate = today.toEpochMilli()
        ),
        Transaction(
            id = 1,
            userId = 0L,
            title = "Food",
            amount = 50f,
            transactionType = Expense.FOOD,
            repeatType = RepeatType.NOT_REPEAT,
            note = "Enjoyed a coffee and a snack at Starbucks with Rick and M.",
            transactionDate = today.toEpochMilli()
        ),
        Transaction(
            id = 2,
            userId = 0L,
            title = "Transport",
            amount = 30f,
            transactionType = Expense.TRANSPORTATION,
            repeatType = RepeatType.NOT_REPEAT,
            transactionDate = yesterday.toEpochMilli()
        ),
        Transaction(
            id = 3,
            userId = 0L,
            title = "Entertainment",
            amount = 20f,
            transactionType = Expense.ENTERTAINMENT,
            repeatType = RepeatType.NOT_REPEAT,
            transactionDate = yesterday.toEpochMilli()
        ),
        Transaction(
            id = 4,
            userId = 0L,
            title = "Clothing",
            amount = 80f,
            transactionType = Expense.CLOTHING,
            repeatType = RepeatType.NOT_REPEAT,
            transactionDate = yesterday.toEpochMilli()
        )
    )

    return transactions.groupBy { Instant.ofEpochMilli(it.transactionDate) }
}

