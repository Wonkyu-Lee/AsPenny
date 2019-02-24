package io.blazeq.aspenny.model

import com.google.firebase.database.Exclude
import com.kizitonwose.time.Day
import com.kizitonwose.time.Interval
import com.kizitonwose.time.minus
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

data class User(
    val id: String
)


// TODO: http://www.daleseo.com/java8-duration-period/ 참고하여 기 존재하는 것 사용하자!
class Period {
    val days get() = cal.get(Calendar.DATE)
    val hours get() = cal.get(Calendar.HOUR_OF_DAY)
    val minutes get() = cal.get(Calendar.MINUTE)
    val seconds get() = cal.get(Calendar.SECOND)

    private val cal: Calendar = Calendar.getInstance()
    constructor(milliSec: Long) {
        cal.time = Date(milliSec)
    }
}

data class Source (
    val type: String,
    val unitPrice: Double,
    val amountPerDay: Double,
    val start: String,
    var end: String = ""
) {
    // default constructor for FireBase
    constructor(): this("unknown", 0.0, 0.0, now.toStr())

    companion object {
        private val now: Calendar get() = Calendar.getInstance()
        private const val pattern = "yyyy-MM-dd-hh:mm:ss"

        fun cigarette(duration: Interval<Day>, amountPerDay: Double, unitPrice: Double): Source {
            return create("cigarette", duration, amountPerDay, unitPrice)
        }

        fun cigarette(amountPerDay: Double, unitPrice: Double): Source {
            return create("cigarette", amountPerDay, unitPrice)
        }

        private fun create(type: String, duration: Interval<Day>, amountPerDay: Double, unitPrice: Double): Source {
            val start = now - duration
            return Source(type, unitPrice, amountPerDay, start.toStr())
        }

        private fun create(type: String, amountPerDay: Double, unitPrice: Double): Source {
            return Source(type, unitPrice, amountPerDay, start = now.toStr())
        }

        private fun getPeriodInMillis(start: Calendar, end: Calendar): Long {
            return end.timeInMillis - start.timeInMillis
        }

        private fun getPeriodInDays(start: Calendar, end: Calendar): Long {
            return TimeUnit.MILLISECONDS.toDays(getPeriodInMillis(start, end))
        }

        private fun Calendar.toStr(): String {
            val format = SimpleDateFormat(pattern)
            format.timeZone = this.timeZone
            return format.format(this.time)
        }

        private fun String.toCal(): Calendar {
            if (this.isEmpty()) {
                return now
            }

            val calendar = Calendar.getInstance()
            val format = SimpleDateFormat(pattern)
            calendar.time = format.parse(this)
            return calendar
        }
    }

    @get:Exclude
    val periodInDays get() = getPeriodInDays(start.toCal(), end.toCal())

    @get:Exclude
    val periodInMillis get() = getPeriodInMillis(start.toCal(), end.toCal())

    @get:Exclude
    val periodDays: Int get() {
        val cal = Calendar.getInstance()
        cal.time = Date(periodInMillis)
        return cal.get(Calendar.DATE)
    }



    @get:Exclude
    val bearing get() = end.isEmpty()

    @get:Exclude
    val sum get() = periodInDays * amountPerDay * unitPrice

    fun quitBearing() {
        end = now.toStr()
    }
}

