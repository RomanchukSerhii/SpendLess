package com.serhiiromanchuk.core.presentation.ui

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object InstantFormatter {
    private val zoneId: ZoneId = ZoneId.systemDefault()
    private val englishLocal: Locale = Locale("en", "US")

    fun formatToRelativeDay(instant: Instant): UiText {
        val today = LocalDate.now(zoneId)
        val yesterday = today.minusDays(1)

        return when (val date = instant.atZone(zoneId).toLocalDate()) {
            today -> UiText.StringResource(R.string.today)
            yesterday -> UiText.StringResource(R.string.yesterday)
            else -> UiText.DynamicString(
                date.format(
                    DateTimeFormatter.ofPattern(
                        "EEEE, MMM d",
                        englishLocal
                    )
                )
            )
        }
    }

    fun getCurrentDayOfWeek(): String {
        return Instant.now()
            .atZone(zoneId)
            .format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH))
    }

    fun getCurrentDayOfMonth(): String {
        return Instant.now()
            .atZone(zoneId)
            .format(DateTimeFormatter.ofPattern("d", Locale.ENGLISH))
    }

    fun getCurrentMonthAndDay(): String {
        return Instant.now()
            .atZone(zoneId)
            .format(DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH))
    }
}