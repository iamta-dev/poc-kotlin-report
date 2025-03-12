package com.ktb.dcb.report.service

import com.ktb.dcb.report.model.request.BillingReport
import com.ktb.dcb.report.model.request.BillingTransaction
import com.ktb.dcb.report.model.request.CashAndLoanTransaction
import com.ktb.dcb.report.model.request.PreviousTransaction
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * Service สำหรับสร้าง Billing Report ในรูปแบบ PDF
 */
@Service
class GenerateBillingReportService {
        /**
         * สร้าง Billing Report จากข้อมูลที่ได้รับ
         * 
         * @param billingStatement ข้อมูลสำหรับสร้าง Billing Report
         * @return ResponseEntity ที่มี PDF เป็น body
         */
        fun exec(billingStatement: BillingReport): ResponseEntity<ByteArray> {
                // โหลด template จาก resources
                val jasperStream =
                        this::class.java.getResourceAsStream("/reports/billing_template.jasper")
                                ?: throw IllegalArgumentException(
                                        "Billing template not found"
                                )

                // สร้าง data source จากข้อมูลที่ได้รับ
                val dataSource = JRBeanCollectionDataSource(listOf(billingStatement))
                val parameters = mutableMapOf<String, Any>()

                // สร้าง data source สำหรับตารางต่างๆ
                val tablePreviousTransaction =
                        JRBeanCollectionDataSource(billingStatement.previousTransactions)
                val tablePreviousTransactionSummary =
                        JRBeanCollectionDataSource(
                                listOf(
                                        PreviousTransaction(
                                                "",
                                                "",
                                                "ยอดรวมที่ชำระในรอบบิลก่อนหน้า",
                                                billingStatement.totalRepayment.toString(),
                                                ""
                                        ),
                                        PreviousTransaction(
                                                "",
                                                "",
                                                "ยอดเงินเรียกเก็บคงค้างยกไป",
                                                billingStatement.outstandingBalanceCarriedForward
                                                        .toString(),
                                                "DR"
                                        )
                                )
                        )
                val tableCashAndLoanTransaction =
                        JRBeanCollectionDataSource(billingStatement.cashAndLoanTransactions)
                val tableCashAndLoanTransactionSummary =
                        JRBeanCollectionDataSource(
                                listOf(
                                        CashAndLoanTransaction(
                                                "",
                                                "",
                                                "รวมยอดเงินที่ทำรายการในรอบบิลนี้",
                                                "",
                                                billingStatement.totalAmountOfDrawdownTransaction
                                                        .toString()
                                        )
                                )
                        )
                val tableBillingTransaction =
                        JRBeanCollectionDataSource(billingStatement.billingTransactions)
                val tableBillingTransactionSummary =
                        JRBeanCollectionDataSource(
                                listOf(
                                        BillingTransaction("", "", "", "", "", ""),
                                        BillingTransaction(
                                                "",
                                                "",
                                                "รวมยอดเงินที่ต้องชำระ",
                                                "",
                                                "",
                                                billingStatement.totalMonthlyInstallment.toString()
                                        ),
                                        BillingTransaction("", "", "", "", "", ""),
                                        BillingTransaction(
                                                "",
                                                "",
                                                "ยอดคงค้างจากรอบบิลที่ก่อนหน้า",
                                                "",
                                                "",
                                                billingStatement.outstandingBalanceCarriedForward
                                                        .toString()
                                        ),
                                        BillingTransaction(
                                                "",
                                                "",
                                                "ค่าติดตามทวงถาม",
                                                "",
                                                "",
                                                billingStatement.collectionFee.toString()
                                        ),
                                        BillingTransaction(
                                                "",
                                                "",
                                                "ดอกเบี้ยผิดนัด (" +
                                                        billingStatement.dueDate +
                                                        "-" +
                                                        billingStatement.cutOffDate +
                                                        ")",
                                                "",
                                                "",
                                                billingStatement.lateChargePenalty.toString()
                                        ),
                                        BillingTransaction(
                                                "",
                                                "",
                                                "ค่าธรรมเนียม/เบี้ยปรับ",
                                                "",
                                                "",
                                                billingStatement.fee.toString()
                                        ),
                                        BillingTransaction(
                                                "",
                                                "",
                                                "รวมยอดที่ต้องชำระทั้งสิ้น",
                                                "",
                                                "",
                                                billingStatement.totalPaymentDue.toString()
                                        )
                                )
                        )

                // เพิ่ม parameters สำหรับ JasperReports
                parameters["TablePreviousTransaction"] = tablePreviousTransaction
                parameters["TablePreviousTransactionSummary"] = tablePreviousTransactionSummary
                parameters["TableCashAndLoanTransaction"] = tableCashAndLoanTransaction
                parameters["TableCashAndLoanTransactionSummary"] = tableCashAndLoanTransactionSummary
                parameters["TableBillingTransaction"] = tableBillingTransaction
                parameters["TableBillingTransactionSummary"] = tableBillingTransactionSummary

                // สร้าง JasperPrint จาก template และ data source
                val jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource)

                // แปลง JasperPrint เป็น PDF
                val pdfBytes: ByteArray = JasperExportManager.exportReportToPdf(jasperPrint)

                // สร้าง ResponseEntity ที่มี PDF เป็น body
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=billing.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdfBytes)
        }
}
