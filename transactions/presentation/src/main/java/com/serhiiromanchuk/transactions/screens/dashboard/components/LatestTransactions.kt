package com.serhiiromanchuk.transactions.screens.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serhiiromanchuk.core.presentation.designsystem.MoneyIcon
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTextButton
import com.serhiiromanchuk.core.presentation.ui.InstantFormatter
import com.serhiiromanchuk.transactions.common_components.TransactionItem
import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.transactions.presentation.R
import java.time.Instant

@Composable
fun LatestTransactions(
    latestTransactions: Map<Instant, List<Transaction>>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .fillMaxHeight(),
        contentColor = MaterialTheme.colorScheme.onSurface,
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        if (latestTransactions.isEmpty()) {
            EmptyTransactions()
        } else {
            LatestTransactionsList(
                transactions = latestTransactions,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Composable
private fun EmptyTransactions() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = LocalDensity.current.run {
                        WindowInsets.navigationBars.getBottom(this).toDp()
                    }
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MoneyIcon(fontSize = 96.sp)
            Text(
                text = stringResource(R.string.no_transactions_to_show),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
private fun LatestTransactionsList(
    transactions: Map<Instant, List<Transaction>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TransactionsListHeader(onShowAllClick = {})
        LazyColumn(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            transactions.forEach { (instant, transactions) ->
                // DataHeader
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp).padding(top = 8.dp, bottom = 4.dp),
                        text = InstantFormatter.formatToRelativeDay(instant).asString(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        ),
                    )
                }

                items(transactions, key = { it.id }) { transaction ->
                    TransactionItem(transaction)
                }
            }

        }
    }
}

@Composable
fun TransactionsListHeader(
    onShowAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.latest_transactions),
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        AppTextButton(
            text = stringResource(R.string.show_all),
            onClick = onShowAllClick
        )
    }
}