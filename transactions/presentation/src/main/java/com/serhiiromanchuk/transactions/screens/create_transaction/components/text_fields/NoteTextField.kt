package com.serhiiromanchuk.transactions.screens.create_transaction.components.text_fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.transactions.presentation.R

@Composable
fun NoteTextField(
    state: TextFieldState,
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
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        ),
        decorator = { innerBox ->

            Box(
                modifier = Modifier
                    .wrapContentWidth(),
                contentAlignment = Alignment.Center
            ) {
                innerBox()
                if (state.text.isEmpty() && !isFocused) {
                    NotePlaceholder()
                }
            }
        },
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction
    )
}

@Composable
private fun NotePlaceholder() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.outline,
        )

        Text(
            text = stringResource(R.string.add_note),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFEF7FF
)
@Composable
private fun PreviewNotePlaceholder() {
    SpendLessTheme {
        NotePlaceholder()
    }
}