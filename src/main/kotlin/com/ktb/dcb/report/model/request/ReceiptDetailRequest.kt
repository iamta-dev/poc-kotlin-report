package com.ktb.dcb.report.model.request

import java.math.BigDecimal

class ReceiptReport(

        // 1) Transaction info
        /**
         * The date that transaction has been posted. Format: DD/MM/YYYY (Buddhist era). Example:
         * "25/01/2568"
         */
        val transactionDate: String,

        /**
         * Receipt number (pending discussion with accounting team). Could be null if not yet
         * assigned at the time of this request.
         */
        val receiptNumber: String? = null,

        /**
         * From account name. If a different person repays on behalf of the debtor, this will show
         * that person's saving account name.
         */
        val fromAccountName: String,

        /**
         * Customer Tax ID or Customer ID (not masked). Source: retrieved from design team /
         * back-end system.
         */
        val customerTaxId: String,

        /** Customer address as stored in CDI at the time of loan origination. */
        val customerAddress: String,

        /** Customer email as stored in CDI at the time of loan origination. */
        val customerEmail: String,

        /** Company address (pending requirement—may be null or empty if not required). */
        val companyAddress: String? = null,

        /** Company Tax ID (pending requirement—may be null or empty if not required). */
        val companyTaxId: String? = null,

        // 2) LOC (Line of Credit) / Product info
        /** Customer’s Jai Paa LOC account number. */
        val locAccountNumber: String,

        /**
         * Payment channel (example can be DDR, Branch, etc.). In the case of DDR, you may also
         * store the masked saving account number (XXX-****-XXX). In the case of Branch, you might
         * store branch name or "CASH".
         */
        val paymentChannel: String,

        /** Market code (e.g., "JAI_PAA"), product name, or other relevant descriptor. */
        val marketCode: String,
        val productName: String,

        /** Language preference, e.g., "TH", "EN". */
        val language: String? = "TH",

        /** Interest rate for Jai Paa (at LOC level). */
        val interestRate: BigDecimal,

        // 3) Outstanding balances (as of repayment EOD)
        val outstandingPrincipal: BigDecimal,
        val outstandingInterest: BigDecimal,
        val outstandingPenaltyInterest: BigDecimal,
        val outstandingFee: BigDecimal,

        // 4) Repaid/allocated amounts (as of repayment EOD)
        val repaidPrincipal: BigDecimal,
        val repaidInterest: BigDecimal,
        val repaidPenaltyInterest: BigDecimal,
        val repaidFee: BigDecimal,

        /** The total repayment amount that has been repaid and allocated as of repayment EOD. */
        val totalRepaymentAmount: BigDecimal,
        var logo: String? = null,
)