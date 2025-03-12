package com.ktb.dcb.report.service

import com.ktb.dcb.report.model.request.BillingStatement
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

@Service
class GenerateBillingReportService {
        fun exec(billingStatement: BillingStatement): ResponseEntity<ByteArray> {
                val jasperStream =
                        this::class.java.getResourceAsStream("/reports/billing_template.jasper")
                                ?: throw IllegalArgumentException(
                                        "billing_template template not found"
                                )

                val dataSource = JRBeanCollectionDataSource(listOf(billingStatement))
                val parameters = mutableMapOf<String, Any>()

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

                parameters.put("TablePreviousTransaction", tablePreviousTransaction)
                parameters.put("TablePreviousTransactionSummary", tablePreviousTransactionSummary)
                parameters.put("TableCashAndLoanTransaction", tableCashAndLoanTransaction)
                parameters.put(
                        "TableCashAndLoanTransactionSummary",
                        tableCashAndLoanTransactionSummary
                )
                parameters.put("TableBillingTransaction", tableBillingTransaction)
                parameters.put("TableBillingTransactionSummary", tableBillingTransactionSummary)

                val jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource)

                val pdfBytes: ByteArray = JasperExportManager.exportReportToPdf(jasperPrint)

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=billing.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdfBytes)
        }
}
