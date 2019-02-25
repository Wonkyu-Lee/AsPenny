package io.blazeq.aspenny.timeutils

import com.kizitonwose.time.Interval
import java.text.SimpleDateFormat
import java.util.Calendar

const val TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS(Z)"

fun now(): Calendar = Calendar.getInstance()

fun Calendar.toStr(): String {
    val format = SimpleDateFormat(TIME_PATTERN)
    format.timeZone = this.timeZone
    return format.format(this.time)
}

fun String.toCal(): Calendar {
    if (this.isEmpty()) {
        return now()
    }

    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat(TIME_PATTERN)
    calendar.time = format.parse(this)
    return calendar
}

class Period {
    val daysInRational get() = milliSec.toDouble() / (1000 * 60 * 60 * 24)
    val days: Int
    val hours: Int
    val minutes: Int
    val seconds: Int
    val milliseconds: Int
    private val milliSec: Long

    constructor(milliSec: Long) {
        this.milliSec = milliSec

        var q = milliSec / (1000 * 60 * 60 * 24)
        var r = milliSec % (1000 * 60 * 60 * 24)
        days = q.toInt()

        q = r / (1000 * 60 * 60)
        r %= (1000 * 60 * 60)
        hours = q.toInt()

        q = r / (1000 * 60)
        r %= (1000 * 60)
        minutes = q.toInt()

        q = r / (1000)
        r %= (1000)
        seconds = q.toInt()
        milliseconds = r.toInt()
    }

    companion object {
        fun between(start: Calendar, end: Calendar): Period {
            return Period(end.timeInMillis - start.timeInMillis)
        }

        fun of(interval: Interval<out com.kizitonwose.time.TimeUnit>): Period {
            return Period(interval.inMilliseconds.longValue)
        }
    }
}