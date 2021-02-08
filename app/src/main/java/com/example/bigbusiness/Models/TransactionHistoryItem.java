package com.example.bigbusiness.Models;

public class TransactionHistoryItem {
    private String TransactionMerchant;
    private String TransactionAmount;
    private String Date;
    private String transaction_credited_debited , transaction_type;
    private String invoice = "Invoice";


    public TransactionHistoryItem() {
    }

    public TransactionHistoryItem(String TransactionMerchant, String transactionAmount , String Date , String transaction_type , String transaction_credited_debited) {
        this.TransactionMerchant = TransactionMerchant;
        this.TransactionAmount = transactionAmount;
        this.Date = Date;
        this.transaction_type = transaction_type;
        this.transaction_credited_debited = transaction_credited_debited;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTransaction_credited_debited() {
        return transaction_credited_debited;
    }

    public void setTransaction_credited_debited(String transaction_credited_debited) {
        this.transaction_credited_debited = transaction_credited_debited;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getTransactionMerchant() {
        return TransactionMerchant;
    }

    public String getTransactionAmount() {
        return TransactionAmount;
    }

    public void setTransactionMerchant(String transactionMerchant) {
        TransactionMerchant = transactionMerchant;
    }

    public void setTransactionAmount(String transactionAmount) {
        TransactionAmount = transactionAmount;
    }
}
