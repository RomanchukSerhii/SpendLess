package com.serhiiromanchuk.transactions.screens.create_transaction.components.text_fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
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
        val focusManager = LocalFocusManager.current
        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        CounterpartyTextField(
            state = transactionFieldsState.counterparty,
            isExpense = isExpense,
            modifier = Modifier
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            onKeyboardAction = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )

        TransactionAmountTextField(
            state = transactionFieldsState.amount,
            isExpense = isExpense,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            onKeyboardAction = {
                KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            }
        )

        NoteTextField(
            value = transactionFieldsState.note,
            onValueChange = { onEvent(CreateTransactionUiEvent.NoteTextChanged(it)) }
        )
    }
}