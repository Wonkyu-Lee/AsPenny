package io.blazeq.aspenny

import com.google.gson.Gson
import io.blazeq.aspenny.models.Source
import org.junit.Test

import org.junit.Assert.*

class SourceUnitTest {
    @Test
    fun creation() {
        var src = Source()
        println(src)
    }

    @Test
    fun bearing_isCorrect() {
        val src = Source.cigarette(5, 3.0, 200.0)
        assertEquals(true, src.bearing)
        println(src)

        src.quitBearing()
        assertEquals(false, src.bearing)
        println(src)
    }

    @Test
    fun duration_isCorrect() {
        val src1 = Source.cigarette(5, 3.0, 200.0)
        assertEquals(5, src1.period.days)

        val src2 = Source.cigarette(3.0, 200.0)
        assertEquals(0, src2.period.days)
    }

    @Test
    fun sum_isCorrect() {
        val src1 = Source.cigarette(5, 3.0, 200.0)
        assertEquals(3000.0, src1.sum, 0.1)

        val src2 = Source.cigarette(5, 2.5, 200.0)
        assertEquals(2500.0, src2.sum, 0.1)
    }

    @Test
    fun json_serialization() {
        val src = Source.cigarette(5, 3.0, 200.0)
        val json = Gson().toJson(src)
        println(json)
    }
}