package com.ktb.dcb.report.controller.rest

import org.springframework.web.bind.annotation.*
import com.ktb.dcb.report.service.GenerateBillingReportService
import com.ktb.dcb.report.model.request.BillingReport
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Billing", description = "API สำหรับสร้าง Billing Report")
class BillingController(
    private val generateBillingReportService: GenerateBillingReportService,
) {

    @Operation(
        summary = "สร้าง Billing Report",
        description = "สร้าง Billing Report ในรูปแบบ PDF จากข้อมูลที่ส่งมา"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "สร้าง Billing Report สำเร็จ",
                content = [Content(mediaType = MediaType.APPLICATION_PDF_VALUE)]
            ),
            ApiResponse(
                responseCode = "400",
                description = "ข้อมูลไม่ถูกต้อง",
                content = [Content()]
            ),
            ApiResponse(
                responseCode = "500",
                description = "เกิดข้อผิดพลาดภายในเซิร์ฟเวอร์",
                content = [Content()]
            )
        ]
    )
    @PostMapping("/billing", produces = [MediaType.APPLICATION_PDF_VALUE])
    fun generateReport(
        @Parameter(description = "ข้อมูลสำหรับสร้าง Billing Report", required = true)
        @RequestBody req: BillingReport
    ): ResponseEntity<ByteArray> {
        return generateBillingReportService.exec(req)
    }
}
