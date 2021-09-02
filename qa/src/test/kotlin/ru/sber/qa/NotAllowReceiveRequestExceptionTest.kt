package ru.sber.qa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NotAllowReceiveRequestExceptionTest {

    @Test
    fun throwException() {
        val expected = NotAllowReceiveRequestException()
        val message = expected.message

        assertNotNull(expected)
        assertEquals("Не разрешено принять запрос на справку", message)
    }
}