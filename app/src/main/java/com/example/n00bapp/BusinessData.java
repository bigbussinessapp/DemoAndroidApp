package com.example.n00bapp;

public class BusinessData {
    private String BusinessName;
    private String TransAmount;

    public BusinessData() {
    }

    public BusinessData(String businessName,String transAmount) {
        BusinessName = businessName;
        TransAmount = transAmount;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public String getTransAmount() {
        return TransAmount;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public void setTransAmount(String transAmount) {
        TransAmount = transAmount;
    }
}
