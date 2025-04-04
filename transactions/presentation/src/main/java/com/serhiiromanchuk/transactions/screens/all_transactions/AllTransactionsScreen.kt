package com.serhiiromanchuk.transactions.screens.all_transactions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFAB
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTopBar
import com.serhiiromanchuk.core.presentation.ui.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.transactions.common_components.TransactionsList
import com.serhiiromanchuk.transactions.presentation.R
import com.serhiiromanchuk.transactions.screens.TransactionsSharedViewModel
import com.serhiiromanchuk.transactions.screens.all_transactions.handling.AllTransactionsUiState
import com.serhiiromanchuk.transactions.screens.all_transactions.handling.AllTransactionsUiEvent
import com.serhiiromanchuk.transactions.screens.create_transaction.CreateTransactionBottomSheet
import com.serhiiromanchuk.transactions.screens.export_transactions.ExportBottomSheet
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllTransactionsScreenRoot(
    onBackClick: () -> Unit,
    viewModel: TransactionsSharedViewModel = koinViewModel()
) {
    BaseContentLayout(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.all_transactions),
                onBackClick = onBackClick,
                onExportClick = {
                    viewModel.onEvent(AllTransactionsUiEvent.ExportSheetToggled)
                }
            )
        },
        floatingActionButton = {
            AppFAB(
                onClick = { viewModel.onEvent(AllTransactionsUiEvent.CreateTransactionSheetToggled) }
            )
        }
    ) {
        AllTransactionsScreen(
            state = viewModel.allTransactionsState
        )
    }

    if (viewModel.createTransactionState.isCreateTransactionSheetOpen) {
        CreateTransactionBottomSheet(
            state = viewModel.createTransactionState,
            onEvent = viewModel::onEvent
        )
    }

    if (viewModel.exportTransactionState.isExportSheetOpen) {
        ExportBottomSheet(
            state = viewModel.exportTransactionState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
private fun AllTransactionsScreen(
    state: AllTransactionsUiState
) {
    TransactionsList(
        transactions = state.transactions,
        amountSettings = state.amountSettings
    )
}

@Preview
@Composable
private fun AllTransactionsScreenPreview() {
    SpendLessTheme {
        AllTransactionsScreen(
            state = AllTransactionsUiState()
        )
    }
}