package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.random.Random
import kotlin.test.assertEquals

internal class ScannerTest {
    @Test
    fun getScanData() {
        var expected = Random.nextBytes(100)
        mockkObject(Random)
        every { Random.nextLong(5000L, 15000L) } returns 1L

        assertDoesNotThrow({ Scanner.getScanData() })
        assertEquals(expected.size, Scanner.getScanData().size)
    }

    @Test
    fun throwScanTimeoutException() {
        mockkObject(Random)
        every { Random.nextLong(5000L, 15000L) } returns Scanner.SCAN_TIMEOUT_THRESHOLD + 1

        assertThrows(ScanTimeoutException::class.java, { Scanner.getScanData() })
    }
}