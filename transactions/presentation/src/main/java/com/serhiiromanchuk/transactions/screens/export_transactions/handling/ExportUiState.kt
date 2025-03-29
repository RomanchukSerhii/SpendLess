package com.serhiiromanchuk.transactions.screens.export_transactions.handling

import com.serhiiromanchuk.transactions.presentation.R

data class ExportUiState(
    val isExportSheetOpen: Boolean = false,
    val exportRange: ExportRange = ExportRange.THREE_MONTH
)

enum class ExportRange(val titleRes: Int) {
    THREE_MONTH(R.string.last_three_months),
    LAST_MONTH(R.string.last_month),
    CURRENT_MONTH(R.string.current_month),
    ALL(R.string.all_data)
}
