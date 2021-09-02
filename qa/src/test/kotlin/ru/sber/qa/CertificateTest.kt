package ru.sber.qa

import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class CertificateTest {
    private var certificateRequest = mockk<CertificateRequest>()
    private var processedBy = 20L
    private var data = Random.nextBytes(100)
    private var certificate = Certificate(certificateRequest, processedBy, data)

    @Test
    fun getCertificateRequest() {
        assertEquals(certificateRequest, certificate.certificateRequest)
    }

    @Test
    fun getProcessedBy() {
        assertEquals(processedBy, certificate.processedBy)
    }

    @Test
    fun getData() {
        assertEquals(data, certificate.data)
    }
}