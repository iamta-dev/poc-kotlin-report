package com.ktb.dcb.report.model.request

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "ข้อมูลสำหรับสร้าง Receipt Report")
data class ReceiptReport(

        // 1) Transaction info
        @Schema(
                description = "วันที่ทำรายการ (รูปแบบ: DD/MM/YYYY พุทธศักราช)",
                example = "25/01/2568"
        )
        val transactionDate: String,

        @Schema(
                description = "เลขที่ใบเสร็จ (อาจเป็น null หากยังไม่ได้กำหนด ณ เวลาที่ส่งคำขอนี้)",
                example = "REC123456789",
                nullable = true
        )
        val receiptNumber: String? = null,

        @Schema(
                description = "ชื่อบัญชีต้นทาง หากมีบุคคลอื่นชำระหนี้แทนลูกหนี้ จะแสดงชื่อบัญชีเงินฝากของบุคคลนั้น",
                example = "นายสมชาย ใจดี"
        )
        val fromAccountName: String,

        @Schema(
                description = "เลขประจำตัวผู้เสียภาษีของลูกค้าหรือรหัสลูกค้า (ไม่ปิดบัง)",
                example = "1234567890123"
        )
        val customerTaxId: String,

        @Schema(
                description = "ที่อยู่ของลูกค้าตามที่เก็บไว้ใน CDI ณ เวลาที่เริ่มสินเชื่อ",
                example = "123 ถนนสุขุมวิท แขวงคลองเตย เขตคลองเตย กรุงเทพฯ 10110"
        )
        val customerAddress: String,

        @Schema(
                description = "อีเมลของลูกค้าตามที่เก็บไว้ใน CDI ณ เวลาที่เริ่มสินเชื่อ",
                example = "somchai@example.com"
        )
        val customerEmail: String,

        @Schema(
                description = "ที่อยู่บริษัท (อาจเป็น null หรือว่างเปล่าหากไม่จำเป็น)",
                example = "789 ถนนสีลม แขวงสีลม เขตบางรัก กรุงเทพฯ 10500",
                nullable = true
        )
        val companyAddress: String? = null,

        @Schema(
                description = "เลขประจำตัวผู้เสียภาษีของบริษัท (อาจเป็น null หรือว่างเปล่าหากไม่จำเป็น)",
                example = "0107536000064",
                nullable = true
        )
        val companyTaxId: String? = null,

        // 2) LOC (Line of Credit) / Product info
        @Schema(
                description = "หมายเลขบัญชีสินเชื่อ Jai Paa ของลูกค้า",
                example = "LOC1234567890"
        )
        val locAccountNumber: String,

        @Schema(
                description = "ช่องทางการชำระเงิน (เช่น DDR, Branch) ในกรณีของ DDR อาจเก็บหมายเลขบัญชีเงินฝากที่ปิดบังบางส่วน (XXX-****-XXX) ในกรณีของ Branch อาจเก็บชื่อสาขาหรือ \"CASH\"",
                example = "DDR: XXX-****-123"
        )
        val paymentChannel: String,

        @Schema(
                description = "รหัสตลาด (เช่น \"JAI_PAA\") หรือตัวระบุที่เกี่ยวข้องอื่นๆ",
                example = "JAI_PAA"
        )
        val marketCode: String,
        
        @Schema(
                description = "ชื่อผลิตภัณฑ์",
                example = "สินเชื่อใจป้า"
        )
        val productName: String,

        @Schema(
                description = "ภาษาที่ต้องการ เช่น \"TH\", \"EN\"",
                example = "TH",
                nullable = true
        )
        val language: String? = "TH",

        @Schema(
                description = "อัตราดอกเบี้ยสำหรับสินเชื่อใจป้า (ระดับ LOC)",
                example = "15.00"
        )
        val interestRate: BigDecimal,

        // 3) Outstanding balances (as of repayment EOD)
        @Schema(
                description = "ยอดคงค้างเงินต้น (ณ สิ้นวันที่ชำระเงิน)",
                example = "50000.00"
        )
        val outstandingPrincipal: BigDecimal,
        
        @Schema(
                description = "ยอดคงค้างดอกเบี้ย (ณ สิ้นวันที่ชำระเงิน)",
                example = "2500.00"
        )
        val outstandingInterest: BigDecimal,
        
        @Schema(
                description = "ยอดคงค้างดอกเบี้ยผิดนัด (ณ สิ้นวันที่ชำระเงิน)",
                example = "0.00"
        )
        val outstandingPenaltyInterest: BigDecimal,
        
        @Schema(
                description = "ยอดคงค้างค่าธรรมเนียม (ณ สิ้นวันที่ชำระเงิน)",
                example = "0.00"
        )
        val outstandingFee: BigDecimal,

        // 4) Repaid/allocated amounts (as of repayment EOD)
        @Schema(
                description = "เงินต้นที่ชำระแล้ว (ณ สิ้นวันที่ชำระเงิน)",
                example = "5000.00"
        )
        val repaidPrincipal: BigDecimal,
        
        @Schema(
                description = "ดอกเบี้ยที่ชำระแล้ว (ณ สิ้นวันที่ชำระเงิน)",
                example = "500.00"
        )
        val repaidInterest: BigDecimal,
        
        @Schema(
                description = "ดอกเบี้ยผิดนัดที่ชำระแล้ว (ณ สิ้นวันที่ชำระเงิน)",
                example = "0.00"
        )
        val repaidPenaltyInterest: BigDecimal,
        
        @Schema(
                description = "ค่าธรรมเนียมที่ชำระแล้ว (ณ สิ้นวันที่ชำระเงิน)",
                example = "0.00"
        )
        val repaidFee: BigDecimal,

        @Schema(
                description = "ยอดรวมการชำระเงินที่ได้ชำระและจัดสรรแล้ว ณ สิ้นวันที่ชำระเงิน",
                example = "5500.00"
        )
        val totalRepaymentAmount: BigDecimal,
)
