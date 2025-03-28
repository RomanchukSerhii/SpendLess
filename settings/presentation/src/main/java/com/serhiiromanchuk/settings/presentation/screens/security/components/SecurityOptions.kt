package com.serhiiromanchuk.settings.presentation.screens.security.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.serhiiromanchuk.core.presentation.designsystem.components.OptionText
import com.serhiiromanchuk.core.presentation.designsystem.components.SegmentOption
import com.serhiiromanchuk.settings.presentation.R

enum class BiometricsPromptUi(override val label: @Composable () -> Unit) : SegmentOption {
    ENABLE(
        label = {
            OptionText(text = stringResource(R.string.enable))
        }
    ),
    DISABLE(
        label = {
            OptionText(text = stringResource(R.string.disable))
        }
    )
}

enum class SessionExpiryDurationUi(override val label: @Composable () -> Unit) : SegmentOption {
    FIVE_MIN(label = { OptionText("5 min") }),
    FIFTEEN_MIN(label = { OptionText("15 min") }),
    THIRTY_MIN(label = { OptionText("30 min") }),
    HOUR(label = { OptionText("1 hour") })
}

enum class LockedOutDurationUi(override val label: @Composable () -> Unit) : SegmentOption {
    FIFTEEN_SEC(label = { OptionText("15s") }),
    THIRTY_SEC(label = { OptionText("30s") }),
    ONE_MIN(label = { OptionText("1 min") }),
    FIVE_MIN(label = { OptionText("5 min") })
}