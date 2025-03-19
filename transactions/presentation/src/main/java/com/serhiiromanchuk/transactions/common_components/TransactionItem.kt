package com.serhiiromanchuk.transactions.common_components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serhiiromanchuk.core.presentation.designsystem.IncomeIcon
import com.serhiiromanchuk.core.presentation.designsystem.NotesIcon
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors
import com.serhiiromanchuk.core.domain.entity.Expense
import com.serhiiromanchuk.core.domain.entity.Income
import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ExpensesFormatUi
import com.serhiiromanchuk.transactions.presentation.R
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState
import com.serhiiromanchuk.transactions.utils.AmountFormatter
import com.serhiiromanchuk.transactions.utils.toUi

@Composable
fun TransactionItem(
    transaction: Transaction,
    amountSettings: DashboardUiState.AmountSettings,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    var isExpanded by remember { mutableStateOf(false) }
    var notePaddingStart by remember { mutableStateOf(Dp.Unspecified) }

    val animatedSurfaceColor by animateColorAsState(
        targetValue = if (isExpanded) AppColors.SurfContainerLowest else MaterialTheme.colorScheme.background,
        label = "surfaceColorAnimation"
    )

    Surface(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                transaction.note?.let {
                    isExpanded = !isExpanded
                }
            },
        shape = RoundedCornerShape(16.dp),
        color = animatedSurfaceColor,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Column(
            modifier = Modifier.animateContentSize()
        ) {
            val horizontalSpace = 8.dp
            val elementPadding = 4.dp
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(elementPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(horizontalSpace)
            ) {
                val formattedAmount = AmountFormatter.getFormatedAmount(
                    newText = transaction.amount.toString(),
                    amountSettings = amountSettings,
                    enforceTwoDecimalPlaces = true
                )

                TransactionIcon(
                    transaction = transaction,
                    modifier = Modifier
                        .onSizeChanged {
                            notePaddingStart = with(density) {
                                (it.width + elementPadding.roundToPx() + horizontalSpace.roundToPx()).toDp()
                            }
                        }
                )

                TransactionInfo(transaction)

                // Transaction amount
                Text(
                    text = when (transaction.transactionType) {
                        is Expense -> when (amountSettings.expensesFormat) {
                            ExpensesFormatUi.MINUS -> "-\$$formattedAmount"
                            ExpensesFormatUi.PARENTHESES -> "(\$$formattedAmount)"
                        }
                        is Income -> "\$$formattedAmount"
                    },
                    modifier = Modifier.padding(end = 4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = when (transaction.transactionType) {
                        is Expense -> MaterialTheme.colorScheme.onSurface
                        is Income -> AppColors.Success
                    }
                )
            }

            // Transaction note
            if (isExpanded) {
                transaction.note?.let { note ->
                    Text(
                        text = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = notePaddingStart,
                                end = 4.dp,
                                top = 2.dp,
                                bottom = 10.dp
                            ),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun TransactionIcon(
    transaction: Transaction,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        // Transaction icon
        val transactionType = transaction.transactionType
        when (transactionType) {
            is Income -> IncomeIcon(
                modifier = Modifier.size(44.dp)
            )

            is Expense -> {
                transactionType.toUi().TextIcon(
                    modifier = Modifier.size(44.dp),
                    fontSize = 20.sp
                )
            }
        }

        // Note icon
        transaction.note?.let {
            Surface(
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 2.dp, y = 2.dp),
                shape = RoundedCornerShape(6.dp),
                contentColor = when (transactionType) {
                    is Income -> AppColors.SecondaryFixedDim
                    is Expense -> MaterialTheme.colorScheme.primaryContainer
                },
                color = AppColors.SurfContainerLowest,
                shadowElevation = 1.dp
            ) {
                Icon(
                    modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.Center),
                    imageVector = NotesIcon,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun RowScope.TransactionInfo(
    transaction: Transaction,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.weight(1f)
    ) {
        val categoryName = when (val transactionType = transaction.transactionType) {
            is Income -> stringResource(R.string.income)
            is Expense -> {
                val expenseCategory = transactionType.toUi()
                stringResource(expenseCategory.titleRes)
            }
        }

        Text(
            text = transaction.title,
            style = MaterialTheme.typography.labelMedium
        )

        Text(
            text = categoryName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}