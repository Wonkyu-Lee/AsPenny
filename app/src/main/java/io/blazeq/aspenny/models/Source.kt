package io.blazeq.aspenny.models

import com.google.firebase.database.Exclude
import com.kizitonwose.time.days
import com.kizitonwose.time.minus
import io.blazeq.aspenny.timeutils.Period
import io.blazeq.aspenny.timeutils.now
import io.blazeq.aspenny.timeutils.toCal
import io.blazeq.aspenny.timeutils.toStr

data class Source (
    val type: String,
    val unitPrice: Double,
    val amountPerDay: Double,
    val start: String,
    var end: String = ""
) {
    // default constructor for FireBase
    constructor(): this("unknown", 0.0, 0.0, now().toStr())

    companion object {
        // TODO: durationDays를 Interval로 바꾸자.
        fun cigarette(durationDays: Long, amountPerDay: Double, unitPrice: Double): Source {
            return create("cigarette", durationDays, amountPerDay, unitPrice)
        }

        fun cigarette(amountPerDay: Double, unitPrice: Double): Source {
            return create("cigarette", amountPerDay, unitPrice)
        }

        private fun create(type: String, durationDays: Long, amountPerDay: Double, unitPrice: Double): Source {
            val start = now() - durationDays.days
            return Source(type, unitPrice, amountPerDay, start.toStr())
        }

        private fun create(type: String, amountPerDay: Double, unitPrice: Double): Source {
            return Source(type, unitPrice, amountPerDay, start = now().toStr())
        }
    }

    @get:Exclude
    val period get() = Period.between(start.toCal(), end.toCal())

    @get:Exclude
    val bearing get() = end.isEmpty()

    @get:Exclude
    val sum get() = period.daysInRational * amountPerDay * unitPrice

    fun quitBearing() {
        end = now().toStr()
    }
}