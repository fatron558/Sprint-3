package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.*

internal class HrDepartmentTest {
    private var certificateRequest = mockk<CertificateRequest>()

    @Test
    fun throwWeekendDayException() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-04T10:00:00Z"), ZoneOffset.UTC)
        assertThrows(WeekendDayException::class.java, { HrDepartment.receiveRequest(certificateRequest) })
    }

    @Test
    fun throwNotAllowReceiveRequestException() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-07T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        assertThrows(NotAllowReceiveRequestException::class.java, { HrDepartment.receiveRequest(certificateRequest) })
    }

    @Test
    fun receiveRequest() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-07T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK
        assertDoesNotThrow({ HrDepartment.receiveRequest(certificateRequest) })
    }

    @Test
    fun processNextRequest() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-07T10:00:00Z"), ZoneOffset.UTC)
        var certificate = mockk<Certificate>()
        var hrEmployeeNumber = 10L

        every { certificateRequest.process(hrEmployeeNumber)} returns certificate
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        HrDepartment.receiveRequest(certificateRequest)
        assertDoesNotThrow({ HrDepartment.processNextRequest(hrEmployeeNumber) })
    }
}