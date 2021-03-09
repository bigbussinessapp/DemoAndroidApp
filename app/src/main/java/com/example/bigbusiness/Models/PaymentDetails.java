package com.example.bigbusiness.Models;

public class PaymentDetails {
    private String curr_salary;
    private String baseSalary;
    private String bonus;
    private String lastPaidOn;
    private String amountPaid;
    private String advancePayment;
    private String taxDetails;
    private String providentFund;
    private String paymentDue; // curr_Salary - amountPaid
    private EmployeeIncentives incentives;
    private EmployeePenalties penalties;
    private BankDetails bankDetails;

    public PaymentDetails(String curr_salary, String baseSalary, String bonus, String lastPaidOn, String amountPaid, String advancePayment, String taxDetails, String providentFund, EmployeeIncentives incentives, EmployeePenalties penalties, BankDetails bankDetails) {
        this.curr_salary = curr_salary;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.lastPaidOn = lastPaidOn;
        this.amountPaid = amountPaid;
        this.advancePayment = advancePayment;
        this.taxDetails = taxDetails;
        this.providentFund = providentFund;
        this.incentives = incentives;
        this.penalties = penalties;
        this.bankDetails = bankDetails;
        setPaymentDue(this.paymentDue);
    }

    public String getCurr_salary() {
        return curr_salary;
    }

    public void setCurr_salary(String curr_salary) {
        this.curr_salary = curr_salary;
    }

    public String getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(String baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getLastPaidOn() {
        return lastPaidOn;
    }

    public void setLastPaidOn(String lastPaidOn) {
        this.lastPaidOn = lastPaidOn;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(String advancePayment) {
        this.advancePayment = advancePayment;
    }

    public String getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(String taxDetails) {
        this.taxDetails = taxDetails;
    }

    public String getProvidentFund() {
        return providentFund;
    }

    public void setProvidentFund(String providentFund) {
        this.providentFund = providentFund;
    }

    public String getPaymentDue() {
        return this.paymentDue;
    }

    public void setPaymentDue(String paymentDue) {
        this.paymentDue = String.valueOf(Double.parseDouble(this.curr_salary)-Double.parseDouble(this.amountPaid));
    }

    public EmployeeIncentives getIncentives() {
        return incentives;
    }

    public void setIncentives(EmployeeIncentives incentives) {
        this.incentives = incentives;
    }

    public EmployeePenalties getPenalties() {
        return penalties;
    }

    public void setPenalties(EmployeePenalties penalties) {
        this.penalties = penalties;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }
}
