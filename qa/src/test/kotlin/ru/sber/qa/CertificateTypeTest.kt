package ru.sber.qa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CertificateTypeTest {

    @Test
    fun nameNdfl() {
        assertEquals("NDFL", CertificateType.NDFL.name)
    }

    @Test
    fun nameLabourBook() {
        assertEquals("LABOUR_BOOK", CertificateType.LABOUR_BOOK.name)
    }

    @Test
    fun count() {
        assertEquals(2, CertificateType.values().size)
    }
}