package com.ktb.dcb.report.controller.rest

import org.springframework.web.bind.annotation.*
import com.ktb.dcb.report.service.GenerateBillingReportService
import com.ktb.dcb.report.model.request.BillingReport
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/v1")
class BillingController(
    private val generateReportService: GenerateBillingReportService,
) {

    @PostMapping("/billing")
    fun generateReport(@RequestBody req: BillingReport): ResponseEntity<ByteArray> {
        return generateReportService.exec(req)
    }

}
