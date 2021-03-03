package com.example.bigbusiness.Models;

public class EmployeePenalties {

    private String amount;
    private String purpose;

    public EmployeePenalties(String amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
