package com.serhiiromanchuk.core.domain.util

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

object InstantFormatter {
    private val zoneId: ZoneId = ZoneId.systemDefault()
    private val englishLocal: Locale = Locale.forLanguageTag("en-US")

    fun formatDateString(timestamp: Long): String {
        val instant = Instant.ofEpochMilli(timestamp)
        val date = instant.atZone(zoneId).toLocalDate()

        return date.format(DateTimeFormatter.ofPattern("MMM d, yyyy", englishLocal))
    }

    fun formatDateTimeString(timestamp: Long): String {
        val instant = Instant.ofEpochMilli(timestamp)
        val dateTime = instant.atZone(zoneId).toLocalDateTime()

        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", englishLocal))
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