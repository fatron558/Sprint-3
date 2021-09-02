package ru.sber.qa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ScanTimeoutExceptionTest {

    @Test
    fun throwException() {
        val expected = ScanTimeoutException()
        val message = expected.message

        assertNotNull(expected)
        assertEquals("Таймаут сканирования документа", message)
    }
}