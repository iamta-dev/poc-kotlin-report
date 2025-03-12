package com.ktb.dcb.report.model.request

data class BillingReport(
    val billingStatementReferenceNumber: String,    // 1
    val documentType: Int,                          // 2
    val documentFormat: Int,                        // 3
    val generationDate: String,                     // 4 (format: DD/MM/YY)
    val customerFullName: String,                   // 5
    val customerAddress: String,                    // 6
    val email: String,                              // 7
    val accountNumber: String,                      // 8
    val cutOffDate: String,                         // 9 (format: DD/MM/YY)
    val dueDate: String,                            // 10 (format: DD/MM/YY)
    val creditLine: Double,                         // 11
    val ledgerBalance: Double,                      // 12
    val availableCreditLimit: Double,               // 13
    val totalPaymentDue: Double,                    // 14
    val previousTransactions: List<PreviousTransaction>,    // 15, 16, 17, 18, 19, 20
    val totalRepayment: Double,                     // 21
    val outstandingBalanceCarriedForward: Double,   // 22
    val cashAndLoanTransactions: List<CashAndLoanTransaction>,  // 23, 24, 25, 26, 27
    val totalAmountOfDrawdownTransaction: Double,   // 28
    val billingTransactions: List<BillingTransaction>, // 29, 30, 31, 32, 33, 34
    val totalMonthlyInstallment: Double,            // 35
    val collectionFee: Double,                      // 36
    val lateChargePenalty: Double,                  // 37
    val fee: Double,                                // 38
    val casaAccountNumber: String,                  // 39
    val cifNumber: String,                          // 40
    val transactionSequenceNumber: String,          // 41
)

data class PreviousTransaction(
    val transactionDate: String,                // 15
    val postingDate: String,                    // 16
    val description: String,                    // 17, 18
    val amount: String,                         // 19, 20
    val note: String,                           // DR
)

data class CashAndLoanTransaction(
    val transactionDate: String,                // 23
    val postingDate: String,                    // 24
    val description: String,                    // 25
    val refId: String,                          // 26
    val amount: String                          // 27
)

data class BillingTransaction(
    val transactionDate: String,                // 29
    val postingDate: String,                    // 30
    val description: String,                    // 31
    val refId: String,                          // 32
    val drawdownInstallmentBilled: String,      // 33
    val amount: String                          // 34
)