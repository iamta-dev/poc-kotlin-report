package com.ktb.dcb.report.controller.rest

import org.springframework.web.bind.annotation.*
import com.ktb.dcb.report.service.GenerateReportService
import com.ktb.dcb.report.model.request.ReceiptReport
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/v1")
class ReceiptController(
    private val generateReportService: GenerateReportService, 
) {

    @PostMapping("/receipt")
    fun generateReport(@RequestBody req: ReceiptReport): ResponseEntity<ByteArray> {
        return generateReportService.exec(req)
    }

}
