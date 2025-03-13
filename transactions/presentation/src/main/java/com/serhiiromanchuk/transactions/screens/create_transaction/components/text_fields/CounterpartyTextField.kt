package com.serhiiromanchuk.transactions.screens.create_transaction.components.text_fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.transactions.presentation.R

@Composable
fun CounterpartyTextField(
    state: TextFieldState,
    isExpense: Boolean,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        state = state,
        modifier = modifier
            .wrapContentWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            },
        textStyle = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerBox ->
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                innerBox()
                if (state.text.isEmpty() && !isFocused) {
                    CounterpartyPlaceholder(isExpense)
                }
            }
        },
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction
    )
}

@Composable
fun CounterpartyPlaceholder(
    isExpense: Boolean,
    modifier: Modifier = Modifier
) {
    Text(
        text = if (isExpense) {
            stringResource(R.string.receiver)
        } else {
            stringResource(R.string.sender)
        },
        style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.outline
        ),
        modifier = modifier
    )
}