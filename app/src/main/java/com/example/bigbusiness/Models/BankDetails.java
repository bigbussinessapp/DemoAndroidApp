package com.example.bigbusiness.Models;

public class BankDetails {
    private String accountNumber;
    private String ifsc;
    private String branch;
    private String bankName;
    private String accountHolderName;

    public BankDetails(String accountNumber, String ifsc, String branch, String bankName, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.ifsc = ifsc;
        this.branch = branch;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
}
