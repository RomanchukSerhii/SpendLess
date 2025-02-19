@file:OptIn(ExperimentalMaterial3Api::class)

package com.serhiiromanchuk.transactions.screens.dashboard.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.transactions.screens.create_transaction.CreateTransactionBottomSheetRoot
import com.serhiiromanchuk.transactions.screens.create_transaction.CreateTransactionViewModel

@Composable
fun CreateTransactionBottomSheet(
    viewModel: CreateTransactionViewModel,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val bottomSheetHeight = screenHeight * 0.93f

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        CreateTransactionBottomSheetRoot(
            viewModel = viewModel,
            modifier = Modifier.height(bottomSheetHeight),
            onCloseClick = onDismissRequest
        )
    }
}