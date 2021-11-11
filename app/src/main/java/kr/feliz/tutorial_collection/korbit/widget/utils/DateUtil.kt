package kr.feliz.tutorial_collection.korbit.widget.utils

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDate.toFormattedString(format: String): String? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        format(formatter)
    } catch (e: Exception) {
        null
    }
}

fun LocalDateTime.toFormattedString(format: String): String? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        format(formatter)
    } catch (e: Exception) {
        null
    }
}

fun Long.toFormattedString(format: String): String? {
    return try {
        val dateFormatter = DateTimeFormatter.ofPattern(format)
        val instant = Instant.ofEpochMilli(this)
        val offset = OffsetDateTime.ofInstant(instant, ZoneId.systemDefault())
        dateFormatter.format(offset)
    } catch (e: Exception) {
        null
    }
}

fun LocalDate.toEpochSecond(): Long {
    return this.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
}

fun LocalDateTime.toEpochSecond(): Long {
    return atZone(ZoneId.systemDefault()).toEpochSecond()
}

fun LocalDateTime.toEpochMilliSecond(): Long {
    return atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun String.fromISO8601ToEpochSecond(): Long? {
    return try {
        val pattern = DateTimeFormatter.ofPattern(ISO_DATE_TIME_PATTERN)
        val localDateTime = LocalDateTime.parse(this, pattern)
        val zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC)
        zonedDateTime.toInstant().epochSecond
    } catch (e: Exception) {
        null
    }
}

private const val ISO_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ"