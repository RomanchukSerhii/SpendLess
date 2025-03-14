package com.serhiiromanchuk.transactions.screens.create_transaction.components.text_fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.domain.entity.ExpensesFormat
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors
import com.serhiiromanchuk.transactions.presentation.R

@Composable
fun TransactionAmountTextField(
    state: TextFieldState,
    expensesFormat: ExpensesFormat,
    isExpense: Boolean,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    val prefixSign = when (expensesFormat) {
        ExpensesFormat.MINUS -> "-"
        ExpensesFormat.PARENTHESES -> "("
    }

    val prefix = if (isExpense) {
        stringResource(R.string.expense_sign, prefixSign)
    } else stringResource(R.string.income_sign)

    val signsColor = if (isExpense) {
        MaterialTheme.colorScheme.error
    } else AppColors.Success

    BasicTextField(
        state = state,
        modifier = modifier.wrapContentWidth(),
        textStyle = MaterialTheme.typography.displayMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerBox ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrefixSign(
                        sign = prefix,
                        color = signsColor
                    )

                    Box(modifier = Modifier.width(IntrinsicSize.Min)) {
                        AmountPlaceholder(state.text.toString())
                        innerBox()
                    }

                    if (isExpense) {
                        SuffixSign(
                            expensesFormat = expensesFormat,
                            color = signsColor
                        )
                    }
                }
            }
        },
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Number
        ),
        onKeyboardAction = onKeyboardAction
    )
}

@Composable
private fun PrefixSign(
    sign: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = sign,
        modifier = modifier.padding(end = 4.dp),
        style = MaterialTheme.typography.displayMedium,
        color = color
    )
}

@Composable
private fun SuffixSign(
    expensesFormat: ExpensesFormat,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = when (expensesFormat) {
            ExpensesFormat.MINUS -> ""
            ExpensesFormat.PARENTHESES -> stringResource(R.string.suffix_sign)
        },
        modifier = modifier.padding(start = 4.dp),
        style = MaterialTheme.typography.displayMedium,
        color = color
    )
}

@Composable
private fun AmountPlaceholder(
    amount: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = amount.ifEmpty { stringResource(R.string.amount_placeholder) },
        modifier = modifier,
        style = MaterialTheme.typography.displayMedium,
        color = if (amount.isEmpty()) {
            MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
        } else {
            Color.Transparent
        }
    )
}