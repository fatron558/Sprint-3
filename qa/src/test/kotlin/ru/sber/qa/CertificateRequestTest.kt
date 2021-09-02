package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class CertificateRequestTest {

    private var employeeNumber = 8L
    private var certificateType = mockk<CertificateType>()
    private var certificateRequest = CertificateRequest(employeeNumber, certificateType)

    @Test
    fun process() {
        var hrEmployeeNumber = 8L
        var byteArr = Random.nextBytes(100)
        mockkObject(Scanner)

        every { Scanner.getScanData() } returns byteArr
        assertNotNull(certificateRequest.process(hrEmployeeNumber))
        every { Scanner.getScanData() } throws ScanTimeoutException()
        assertThrows(ScanTimeoutException::class.java, { certificateRequest.process(hrEmployeeNumber) })
    }

    @Test
    fun getEmployeeNumber() {
        assertEquals(employeeNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        assertEquals(certificateType, certificateRequest.certificateType)
    }
}