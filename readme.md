# poc-kotlin-report
```
./gradlew build  
./gradlew test
./gradlew bootRun  
```

>POST http://localhost:8080/api/v1/billing
```json
{
  "billingStatementReferenceNumber": "BS12345678",
  "documentType": 1,
  "documentFormat": 2,
  "generationDate": "12/03/25",
  "customerFullName": "สมชาย ใจดี",
  "customerAddress": "123/45 ถนนสุขุมวิท แขวงคลองเตย เขตคลองเตย กรุงเทพมหานคร 10110",
  "email": "somchai@example.com",
  "accountNumber": "1234567890123456",
  "cutOffDate": "10/03/25",
  "dueDate": "25/03/25",
  "creditLine": 100000.00,
  "ledgerBalance": 45678.90,
  "availableCreditLimit": 54321.10,
  "totalPaymentDue": 5000.00,
  "previousTransactions": [
    {
      "transactionDate": "01/02/25",
      "postingDate": "02/02/25",
      "description": "ค่าสินค้า บิ๊กซี สาขาลาดพร้าว",
      "amount": "1250.75",
      "note": "DR"
    },
    {
      "transactionDate": "05/02/25",
      "postingDate": "06/02/25",
      "description": "ค่าอาหาร ร้านอาหารริมน้ำ",
      "amount": "850.00",
      "note": "DR"
    }
  ],
  "totalRepayment": 2000.00,
  "outstandingBalanceCarriedForward": 43678.90,
  "cashAndLoanTransactions": [
    {
      "transactionDate": "08/02/25",
      "postingDate": "09/02/25",
      "description": "เบิกเงินสดล่วงหน้า",
      "refId": "CS123456",
      "amount": "5000.00"
    },
    {
      "transactionDate": "15/02/25",
      "postingDate": "16/02/25",
      "description": "สินเชื่อส่วนบุคคล",
      "refId": "LN789012",
      "amount": "10000.00"
    }
  ],
  "totalAmountOfDrawdownTransaction": 15000.00,
  "billingTransactions": [
    {
      "transactionDate": "18/02/25",
      "postingDate": "19/02/25",
      "description": "ผ่อนชำระสินค้าเครื่องใช้ไฟฟ้า",
      "refId": "INS456789",
      "drawdownInstallmentBilled": "1/12",
      "amount": "1500.00"
    },
    {
      "transactionDate": "20/02/25",
      "postingDate": "21/02/25",
      "description": "ผ่อนชำระโทรศัพท์มือถือ",
      "refId": "INS123456",
      "drawdownInstallmentBilled": "2/24",
      "amount": "1200.00"
    }
  ],
  "totalMonthlyInstallment": 2700.00,
  "collectionFee": 0.00,
  "lateChargePenalty": 0.00,
  "fee": 0.00,
  "casaAccountNumber": "987654321098",
  "cifNumber": "CIF12345678",
  "transactionSequenceNumber": "TXN987654321"
}
```

>POST http://localhost:8080/api/v1/receipt
```json
{
    "transactionDate": "12/03/2568",
    "receiptNumber": "RCT25680312001",
    "fromAccountName": "นายสมชาย ใจดี",
    "customerTaxId": "1234567890123",
    "customerAddress": "123/45 ถนนสุขุมวิท แขวงคลองเตย เขตคลองเตย กรุงเทพมหานคร 10110",
    "customerEmail": "somchai@example.com",
    "companyAddress": "บริษัท เคทีบี จำกัด (มหาชน) 35 ถนนสุขุมวิท แขวงคลองเตยเหนือ เขตวัฒนา กรุงเทพมหานคร 10110",
    "companyTaxId": "0107536000595",
    "locAccountNumber": "1234567890123456",
    "paymentChannel": "DDR 123-****-789",
    "marketCode": "JAI_PAA",
    "productName": "ใจป้า",
    "language": "TH",
    "interestRate": 25.00,
    "outstandingPrincipal": 45000.00,
    "outstandingInterest": 2500.00,
    "outstandingPenaltyInterest": 500.00,
    "outstandingFee": 200.00,
    "repaidPrincipal": 5000.00,
    "repaidInterest": 1200.00,
    "repaidPenaltyInterest": 500.00,
    "repaidFee": 200.00,
    "totalRepaymentAmount": 6900.00
}
```