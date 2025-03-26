package com.serhiiromanchuk.transactions.screens.create_transaction.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.serhiiromanchuk.core.presentation.designsystem.TrendingDownIcon
import com.serhiiromanchuk.core.presentation.designsystem.TrendingUpIcon
import com.serhiiromanchuk.core.presentation.designsystem.components.OptionText
import com.serhiiromanchuk.core.presentation.designsystem.components.SegmentOption
import com.serhiiromanchuk.core.presentation.designsystem.components.AppSegmentedButton
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.transactions.presentation.R

@Composable
fun TransactionModeSelector(
    selectedOption: TransactionModeOptions,
    onOptionClicked: (TransactionModeOptions) -> Unit,
    modifier: Modifier = Modifier,
) {
    AppSegmentedButton(
        segmentOptions = TransactionModeOptions.entries,
        selectedOption = selectedOption,
        onOptionClick = { onOptionClicked(it as TransactionModeOptions) },
        modifier = modifier.fillMaxWidth()
    )
}

enum class TransactionModeOptions(override val label: @Composable () -> Unit) : SegmentOption {
    EXPENSE(
        label = {
            OptionText(
                text = stringResource(R.string.expense),
                leadingIcon = {
                    Icon(
                        imageVector = TrendingDownIcon,
                        contentDescription = null
                    )
                }
            )
        }
    ),
    INCOME(
        label = {
            OptionText(
                text = stringResource(R.string.income),
                leadingIcon = {
                    Icon(
                        imageVector = TrendingUpIcon,
                        contentDescription = null
                    )
                }
            )
        }
    ),
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFEF7FF
)
@Composable
private fun PreviewExpenseIncomeOptions() {
    SpendLessTheme {
        TransactionModeSelector(
            selectedOption = TransactionModeOptions.EXPENSE,
            onOptionClicked = {}
        )
    }
}