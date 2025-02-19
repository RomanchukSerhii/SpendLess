package com.serhiiromanchuk.transactions.screens.create_transaction.components.text_fields

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors
import com.serhiiromanchuk.transactions.presentation.R

@Composable
fun TransactionAmountTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isExpense: Boolean,
    modifier: Modifier = Modifier
) {
    val prefix = if (isExpense) {
        stringResource(R.string.expense_sign)
    } else stringResource(R.string.income_sign)

    val prefixColor = if (isExpense) {
        MaterialTheme.colorScheme.error
    } else AppColors.Success

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.matches(Regex("^[0-9,.]*$"))) {
                onValueChange(newValue)
            }
        },
        modifier = modifier.wrapContentWidth(),
        textStyle = MaterialTheme.typography.displayMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                PrefixText(
                    text = prefix,
                    color = prefixColor
                )

                if (value.isEmpty()) {
                    AmountPlaceholder()
                } else innerTextField()
            }
        }
    )
}

@Composable
private fun PrefixText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.padding(end = 4.dp),
        style = MaterialTheme.typography.displayMedium,
        color = color
    )
}

@Composable
private fun AmountPlaceholder(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.amount_placeholder),
        modifier = modifier,
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
    )
}