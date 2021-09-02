package ru.sber.qa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WeekendDayExceptionTest {

    @Test
    fun throwException() {
        val expected = WeekendDayException()
        val message = expected.message

        assertNotNull(expected)
        assertEquals("Заказ справков в выходной день не работает", message)
    }
}