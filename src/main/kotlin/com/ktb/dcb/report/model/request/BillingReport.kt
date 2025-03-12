package com.ktb.dcb.report.model.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "ข้อมูลสำหรับสร้าง Billing Report")
data class BillingReport(
    @Schema(description = "หมายเลขอ้างอิงใบแจ้งหนี้", example = "BIL123456789")
    val billingStatementReferenceNumber: String,    // 1
    
    @Schema(description = "ประเภทเอกสาร", example = "1")
    val documentType: Int,                          // 2
    
    @Schema(description = "รูปแบบเอกสาร", example = "1")
    val documentFormat: Int,                        // 3
    
    @Schema(description = "วันที่สร้างเอกสาร (รูปแบบ: DD/MM/YY)", example = "01/01/25")
    val generationDate: String,                     // 4 (format: DD/MM/YY)
    
    @Schema(description = "ชื่อเต็มของลูกค้า", example = "นายสมชาย ใจดี")
    val customerFullName: String,                   // 5
    
    @Schema(description = "ที่อยู่ของลูกค้า", example = "123 ถนนสุขุมวิท แขวงคลองเตย เขตคลองเตย กรุงเทพฯ 10110")
    val customerAddress: String,                    // 6
    
    @Schema(description = "อีเมลของลูกค้า", example = "somchai@example.com")
    val email: String,                              // 7
    
    @Schema(description = "หมายเลขบัญชี", example = "1234567890")
    val accountNumber: String,                      // 8
    
    @Schema(description = "วันที่ตัดรอบบัญชี (รูปแบบ: DD/MM/YY)", example = "15/01/25")
    val cutOffDate: String,                         // 9 (format: DD/MM/YY)
    
    @Schema(description = "วันครบกำหนดชำระ (รูปแบบ: DD/MM/YY)", example = "25/01/25")
    val dueDate: String,                            // 10 (format: DD/MM/YY)
    
    @Schema(description = "วงเงินสินเชื่อ", example = "100000.00")
    val creditLine: Double,                         // 11
    
    @Schema(description = "ยอดคงค้างในบัญชี", example = "50000.00")
    val ledgerBalance: Double,                      // 12
    
    @Schema(description = "วงเงินสินเชื่อคงเหลือที่สามารถใช้ได้", example = "50000.00")
    val availableCreditLimit: Double,               // 13
    
    @Schema(description = "ยอดรวมที่ต้องชำระ", example = "5000.00")
    val totalPaymentDue: Double,                    // 14
    
    @Schema(description = "รายการธุรกรรมก่อนหน้า")
    val previousTransactions: List<PreviousTransaction>,    // 15, 16, 17, 18, 19, 20
    
    @Schema(description = "ยอดรวมที่ชำระแล้ว", example = "3000.00")
    val totalRepayment: Double,                     // 21
    
    @Schema(description = "ยอดคงค้างที่ยกไป", example = "2000.00")
    val outstandingBalanceCarriedForward: Double,   // 22
    
    @Schema(description = "รายการเงินสดและเงินกู้")
    val cashAndLoanTransactions: List<CashAndLoanTransaction>,  // 23, 24, 25, 26, 27
    
    @Schema(description = "ยอดรวมของรายการเบิกถอน", example = "10000.00")
    val totalAmountOfDrawdownTransaction: Double,   // 28
    
    @Schema(description = "รายการในใบแจ้งหนี้")
    val billingTransactions: List<BillingTransaction>, // 29, 30, 31, 32, 33, 34
    
    @Schema(description = "ยอดรวมการผ่อนชำระรายเดือน", example = "5000.00")
    val totalMonthlyInstallment: Double,            // 35
    
    @Schema(description = "ค่าธรรมเนียมการติดตามทวงถาม", example = "0.00")
    val collectionFee: Double,                      // 36
    
    @Schema(description = "ค่าปรับจากการชำระล่าช้า", example = "0.00")
    val lateChargePenalty: Double,                  // 37
    
    @Schema(description = "ค่าธรรมเนียม", example = "0.00")
    val fee: Double,                                // 38
    
    @Schema(description = "หมายเลขบัญชีเงินฝาก", example = "0987654321")
    val casaAccountNumber: String,                  // 39
    
    @Schema(description = "หมายเลข CIF", example = "CIF12345")
    val cifNumber: String,                          // 40
    
    @Schema(description = "หมายเลขลำดับธุรกรรม", example = "TXN123456")
    val transactionSequenceNumber: String,          // 41
)

@Schema(description = "ข้อมูลรายการธุรกรรมก่อนหน้า")
data class PreviousTransaction(
    @Schema(description = "วันที่ทำรายการ", example = "01/01/25")
    val transactionDate: String,                // 15
    
    @Schema(description = "วันที่ลงบัญชี", example = "02/01/25")
    val postingDate: String,                    // 16
    
    @Schema(description = "รายละเอียดรายการ", example = "ชำระค่างวด")
    val description: String,                    // 17, 18
    
    @Schema(description = "จำนวนเงิน", example = "5000.00")
    val amount: String,                         // 19, 20
    
    @Schema(description = "หมายเหตุ", example = "DR")
    val note: String,                           // DR
)

@Schema(description = "ข้อมูลรายการเงินสดและเงินกู้")
data class CashAndLoanTransaction(
    @Schema(description = "วันที่ทำรายการ", example = "05/01/25")
    val transactionDate: String,                // 23
    
    @Schema(description = "วันที่ลงบัญชี", example = "06/01/25")
    val postingDate: String,                    // 24
    
    @Schema(description = "รายละเอียดรายการ", example = "เบิกเงินกู้")
    val description: String,                    // 25
    
    @Schema(description = "หมายเลขอ้างอิง", example = "REF123456")
    val refId: String,                          // 26
    
    @Schema(description = "จำนวนเงิน", example = "10000.00")
    val amount: String                          // 27
)

@Schema(description = "ข้อมูลรายการในใบแจ้งหนี้")
data class BillingTransaction(
    @Schema(description = "วันที่ทำรายการ", example = "10/01/25")
    val transactionDate: String,                // 29
    
    @Schema(description = "วันที่ลงบัญชี", example = "11/01/25")
    val postingDate: String,                    // 30
    
    @Schema(description = "รายละเอียดรายการ", example = "ค่างวดประจำเดือน")
    val description: String,                    // 31
    
    @Schema(description = "หมายเลขอ้างอิง", example = "REF789012")
    val refId: String,                          // 32
    
    @Schema(description = "ยอดเงินกู้ที่เรียกเก็บในงวด", example = "4500.00")
    val drawdownInstallmentBilled: String,      // 33
    
    @Schema(description = "จำนวนเงิน", example = "5000.00")
    val amount: String                          // 34
)
