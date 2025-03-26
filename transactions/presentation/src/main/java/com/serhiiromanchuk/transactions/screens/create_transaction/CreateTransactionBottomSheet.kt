@file:OptIn(ExperimentalMaterial3Api::class)

package com.serhiiromanchuk.transactions.screens.create_transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFilledButton
import com.serhiiromanchuk.transactions.presentation.R
import com.serhiiromanchuk.transactions.screens.create_transaction.components.CreateTransactionHeader
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeSelector
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionOptions
import com.serhiiromanchuk.transactions.screens.create_transaction.components.text_fields.TransactionFields
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTransactionBottomSheet(
    state: CreateTransactionUiState,
    onEvent: (CreateTransactionUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight * 0.07f

    ModalBottomSheet(
        onDismissRequest = { toggleSheet(onEvent) },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        modifier = modifier.padding(top = sheetHeight),
        properties = ModalBottomSheetProperties(
            isAppearanceLightStatusBars = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            CreateTransactionHeader(
                onCloseClick = { toggleSheet(onEvent) },
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
                    expensesFormat = state.expensesFormat,
                    onEvent = onEvent,
                    isExpense = state.isExpense
                )
                TransactionOptions(
                    isExpense = state.isExpense,
                    selectedSpendCategory = state.expenseCategory,
                    selectedRepeatingCategory = state.repeatingCategory,
                    onSpendCategoryClick = {
                        onEvent(CreateTransactionUiEvent.SpendCategorySelected(it))
                    },
                    onRepeatingCategoryClick = {
                        onEvent(CreateTransactionUiEvent.RepeatingCategorySelected(it))
                    }
                )
                AppFilledButton(
                    text = stringResource(R.string.create),
                    onClick = { onEvent(CreateTransactionUiEvent.CreateButtonClicked) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    enabled = state.isCreateButtonEnabled
                )
            }
        }
    }
}

private fun toggleSheet(onEvent: (CreateTransactionUiEvent) -> Unit) {
    onEvent(CreateTransactionUiEvent.CreateTransactionSheetToggled)
}