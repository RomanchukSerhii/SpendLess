@file:OptIn(ExperimentalMaterial3Api::class)

package com.serhiiromanchuk.transactions.screens.export_transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFilledButton
import com.serhiiromanchuk.transactions.presentation.R
import com.serhiiromanchuk.transactions.screens.export_transactions.components.ExportRangeDropdown
import com.serhiiromanchuk.transactions.screens.export_transactions.components.ExportSheetHeader
import com.serhiiromanchuk.transactions.screens.export_transactions.handling.ExportUiEvent
import com.serhiiromanchuk.transactions.screens.export_transactions.handling.ExportUiState

@Composable
fun ExportBottomSheet(
    state: ExportUiState,
    onEvent: (ExportUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight * 0.25f

    ModalBottomSheet(
        onDismissRequest = { onEvent(ExportUiEvent.ExportSheetToggled) },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        modifier = modifier.padding(top = sheetHeight),
        properties = ModalBottomSheetProperties(
            isAppearanceLightStatusBars = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ExportSheetHeader(
                onCloseClick = { onEvent(ExportUiEvent.ExportSheetToggled)  },
                modifier = Modifier.offset(y = (-12).dp)
            )

            ExportRangeDropdown(
                selectedItem = state.exportRange,
                onItemSelected = { onEvent(ExportUiEvent.ExportRangeClicked(it)) }
            )

            AppFilledButton(
                text = stringResource(R.string.export),
                onClick = { onEvent(ExportUiEvent.ExportButtonClicked) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}