package com.serhiiromanchuk.settings.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.serhiiromanchuk.core.domain.entity.BiometricsPrompt
import com.serhiiromanchuk.core.domain.entity.LockedOutDuration
import com.serhiiromanchuk.core.domain.entity.SessionExpiryDuration
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

fun BiometricsPrompt.toUi(): BiometricsPromptUi {
    return when (this) {
        BiometricsPrompt.ENABLE -> BiometricsPromptUi.ENABLE
        BiometricsPrompt.DISABLE -> BiometricsPromptUi.DISABLE
    }
}

fun SessionExpiryDuration.toUi(): SessionExpiryDurationUi {
    return when (this) {
        SessionExpiryDuration.FIVE_MIN -> SessionExpiryDurationUi.FIVE_MIN
        SessionExpiryDuration.FIFTEEN_MIN -> SessionExpiryDurationUi.FIFTEEN_MIN
        SessionExpiryDuration.THIRTY_MIN -> SessionExpiryDurationUi.THIRTY_MIN
        SessionExpiryDuration.HOUR -> SessionExpiryDurationUi.HOUR
    }
}

fun LockedOutDuration.toUi(): LockedOutDurationUi {
    return when (this) {
        LockedOutDuration.FIFTEEN_SEC -> LockedOutDurationUi.FIFTEEN_SEC
        LockedOutDuration.THIRTY_SEC -> LockedOutDurationUi.THIRTY_SEC
        LockedOutDuration.ONE_MIN -> LockedOutDurationUi.ONE_MIN
        LockedOutDuration.FIVE_MIN -> LockedOutDurationUi.FIVE_MIN
    }
}