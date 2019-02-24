package io.blazeq.aspenny

import com.google.gson.Gson
import com.kizitonwose.time.days
import io.blazeq.aspenny.model.Period
import io.blazeq.aspenny.model.Source
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun creation() {
        var src = Source()
        println(src)
    }

    @Test
    fun bearing_isCorrect() {
        val src = Source.cigarette(5.days, 3.0, 200.0)
        assertEquals(true, src.bearing)
        println(src)

        src.quitBearing()
        assertEquals(false, src.bearing)
        println(src)
    }

    @Test
    fun duration_isCorrect() {
        val src1 = Source.cigarette(5.days, 3.0, 200.0)
        assertEquals(5, src1.periodInDays)

        val src2 = Source.cigarette(3.0, 200.0)
        assertEquals(0, src2.periodInDays)
    }

    @Test
    fun sum_isCorrect() {
        val src1 = Source.cigarette(5.days, 3.0, 200.0)
        assertEquals(3000.0, src1.sum, 0.1)

        val src2 = Source.cigarette(5.days, 2.5, 200.0)
        assertEquals(2500.0, src2.sum, 0.1)
    }

    @Test
    fun json_serialization() {
        val src = Source.cigarette(5.days, 3.0, 200.0)
        val json = Gson().toJson(src)
        println(json)
    }

    @Test
    fun test_period() {
        val format = SimpleDateFormat("dd-hh:mm:ss")
        val time = format.parse("08-17:30:22")
        val period = Period(time.time)

        assertEquals(period.days, 8)
        assertEquals(period.hours, 17)
        assertEquals(period.minutes, 30)
        assertEquals(period.seconds, 22)
    }
}