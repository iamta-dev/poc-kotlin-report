package com.ktb.dcb.report.service

import com.ktb.dcb.report.model.request.ReceiptReport
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class GenerateReportService() {
        fun exec(receiptDetail: ReceiptReport): ResponseEntity<ByteArray> {
                val jasperStream =
                        this::class.java.getResourceAsStream("/reports/receipt_template.jasper")
                                ?: throw IllegalArgumentException("Report template not found")

                val dataSource = JRBeanCollectionDataSource(listOf(receiptDetail))
                val parameters = mutableMapOf<String, Any>()

                val jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource)

                val pdfBytes: ByteArray = JasperExportManager.exportReportToPdf(jasperPrint)

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=billing.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdfBytes)
        }
}
