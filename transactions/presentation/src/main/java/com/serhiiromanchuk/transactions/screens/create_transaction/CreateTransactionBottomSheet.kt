package com.serhiiromanchuk.transactions.screens.create_transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.FilledButton
import com.serhiiromanchuk.transactions.presentation.R
import com.serhiiromanchuk.transactions.screens.create_transaction.components.CreateTransactionHeader
import com.serhiiromanchuk.transactions.screens.create_transaction.components.text_fields.TransactionFields
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeSelector
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionOptions
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState

@Composable
fun CreateTransactionBottomSheetRoot(
    viewModel: CreateTransactionViewModel,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CreateTransactionBottomSheet(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onCloseClick = onCloseClick,
        modifier = modifier
    )
}

@Composable
private fun CreateTransactionBottomSheet(
    state: CreateTransactionUiState,
    onEvent: (CreateTransactionUiEvent) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        CreateTransactionHeader(
            onCloseClick = onCloseClick,
            modifier = Modifier.offset(y = (-12).dp)
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            TransactionModeSelector(
                selectedOption = state.transactionMode,
                onOptionClicked = { onEvent(CreateTransactionUiEvent.TransactionModeSelected(it)) }
            )
            TransactionFields(
                transactionFieldsState = state.transactionFieldsState,
                onEvent = onEvent,
                isExpense = state.isExpense
            )
            TransactionOptions(
                isExpense = state.isExpense,
                selectedSpendCategory = state.spendCategory,
                selectedRepeatingCategory = state.repeatingCategory,
                onSpendCategoryClick = {
                    onEvent(CreateTransactionUiEvent.SpendCategorySelected(it))
                },
                onRepeatingCategoryClick = {
                    onEvent(CreateTransactionUiEvent.RepeatingCategorySelected(it))
                }
            )
            FilledButton(
                text = stringResource(R.string.create),
                onClick = { onEvent(CreateTransactionUiEvent.CreateButtonClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }
    }
}