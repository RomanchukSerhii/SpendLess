package com.serhiiromanchuk.core.presentation.ui

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
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
                        "MMM d, EEEE",
                        englishLocal
                    )
                )
            )
        }
    }

    fun formatDateString(timestamp: Long): String {
        val instant = Instant.ofEpochMilli(timestamp)
        val date = instant.atZone(zoneId).toLocalDate()

        return date.format(DateTimeFormatter.ofPattern("MMM d, yyyy", englishLocal))
    }

    fun convertInstantToLocalDate(timestamp: Long): Instant {
        return Instant
            .ofEpochMilli(timestamp)
            .atZone(zoneId)
            .toLocalDate()
            .atStartOfDay(zoneId)
            .toInstant()
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

    fun getPreviousWeekRange(): Pair<Instant, Instant> {
        val currentDateTime = LocalDateTime.now(zoneId)

        val lastMonday = currentDateTime.minusWeeks(1)
            .with(DayOfWeek.MONDAY)
            .atZone(zoneId)
            .toInstant()

        val lastSunday = lastMonday.plus(6, ChronoUnit.DAYS)

        return Pair(lastMonday, lastSunday)
    }
}