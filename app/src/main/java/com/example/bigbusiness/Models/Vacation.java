package com.example.bigbusiness.Models;

public class Vacation {
    private String period;
    private String startDate; // dd/mm/yyyy
    private String endDate; // dd/mm/yyyy
    private String status; // approved, submitted, cancelled, rejected
//TO DO : REMOVE FISRT PARAMETER AND AUTO CALCULATE-->PERIOD
    public Vacation(String period, String startDate, String endDate, String status) {
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    //setPeriod(); // endDate - startDate


    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
