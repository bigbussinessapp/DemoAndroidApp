package com.example.bigbusiness.Models;

public class TransactionHistoryItem {
    private String TransactionMerchant;
    private String TransactionAmount;
    private String date;
    private String transaction_credited_debited , transaction_type;
    private String invoiceId;


    public TransactionHistoryItem() {
    }

    public TransactionHistoryItem(String TransactionMerchant, String transactionAmount , String date , String transaction_type , String transaction_credited_debited, String invoiceId) {
        this.TransactionMerchant = TransactionMerchant;
        this.TransactionAmount = transactionAmount;
        this.date = date;
        this.transaction_type = transaction_type;
        this.transaction_credited_debited = transaction_credited_debited;
        this.invoiceId = invoiceId;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransaction_credited_debited() {
        return transaction_credited_debited;
    }

    public void setTransaction_credited_debited(String transaction_credited_debited) {
        this.transaction_credited_debited = transaction_credited_debited;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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
