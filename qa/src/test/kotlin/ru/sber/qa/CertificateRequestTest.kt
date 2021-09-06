package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.random.Random

internal class CertificateRequestTest {


    private var hrEmployeeNumber = 20L
    private var employeeNumber = 8L
    private var certificateType = mockk<CertificateType>()
    private var certificateRequest = CertificateRequest(employeeNumber, certificateType)

    @BeforeEach
    fun beforeTests() {
        mockkObject(Scanner)
    }

    @Test
    fun process() {
        var byteArr = Random.nextBytes(100)

        every { Scanner.getScanData() } returns byteArr
        assertNotNull(certificateRequest.process(hrEmployeeNumber))
    }

    @Test
    fun throwScanTimeoutException() {
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

    @AfterEach
    fun afterTests() {
        unmockkAll()
    }
}