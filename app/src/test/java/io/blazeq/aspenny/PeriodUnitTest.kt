package io.blazeq.aspenny

import com.kizitonwose.time.*
import io.blazeq.aspenny.timeutils.Period
import io.blazeq.aspenny.timeutils.now
import io.blazeq.aspenny.timeutils.toCal
import io.blazeq.aspenny.timeutils.toStr
import org.junit.Assert.assertEquals
import org.junit.Test

class PeriodUnitTest {
    @Test
    fun test_of() {
        val period = Period.of(2500.milliseconds)
        assertEquals(2, period.seconds)
        assertEquals(500, period.milliseconds)
    }

    @Test
    fun test_between01() {
        val c1 = now()
        val c2 = c1 +
                1.days +
                2.hours +
                64.minutes +
                125.seconds +
                2777.milliseconds
        val period = Period.between(c1, c2)
        assertEquals(1, period.days)
        assertEquals(3, period.hours)
        assertEquals(6, period.minutes)
        assertEquals(7, period.seconds)
        assertEquals(777, period.milliseconds)
    }

    @Test
    fun test_between02() {
        val c1 = "2019-02-26 00:00:00.000(+0900)".toCal()
        val c2 = "2019-02-29 02:42:44.546(+0900)".toCal()
        val period = Period.between(c1, c2)
        assertEquals(period.days, 3)
        assertEquals(period.hours, 2)
        assertEquals(period.minutes, 42)
        assertEquals(period.seconds, 44)
        assertEquals(period.milliseconds, 546)
    }

    @Test
    fun test_daysInRational() {
        val c1 = now()
        val c2 = c1 + 3.days + 12.hours
        val period = Period.between(c1, c2)
        assertEquals(period.daysInRational, 3.5, 0.0001)
    }

    @Test
    fun test_converting() {
        val s1 = "2019-01-17 02:42:44.546(+0900)"
        val c1 = s1.toCal()
        val s2 = c1.toStr()
        val c2 = s2.toCal()
        assertEquals(s1, s2)
        assertEquals(c1, c2)
    }
}