package com.serhiiromanchuk.transactions.screens.create_transaction.components.text_fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState.*

@Composable
fun TransactionFields(
    transactionFieldsState: TransactionFieldsState,
    onEvent: (CreateTransactionUiEvent) -> Unit,
    isExpense: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CounterpartyTextField(
            value = transactionFieldsState.counterpartyText,
            onValueChange = { onEvent(CreateTransactionUiEvent.CounterpartyTextChanged(it)) },
            isExpense = isExpense
        )

        TransactionAmountTextField(
            value = transactionFieldsState.amountText,
            onValueChange = { onEvent(CreateTransactionUiEvent.AmountTextChanged(it)) },
            isExpense = isExpense
        )

        NoteTextField(
            value = transactionFieldsState.noteText,
            onValueChange = { onEvent(CreateTransactionUiEvent.NoteTextChanged(it)) }
        )
    }
}