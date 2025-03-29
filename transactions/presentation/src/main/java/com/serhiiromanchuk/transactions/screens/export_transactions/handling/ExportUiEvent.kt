package com.serhiiromanchuk.transactions.screens.export_transactions.handling

sealed interface ExportUiEvent {
    data object ExportSheetToggled : ExportUiEvent
    data object ExportButtonClicked : ExportUiEvent
    data class ExportRangeClicked(val exportRange: ExportRange) : ExportUiEvent
}