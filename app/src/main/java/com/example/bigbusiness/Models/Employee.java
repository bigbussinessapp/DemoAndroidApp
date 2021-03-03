package com.example.bigbusiness.Models;

import java.util.List;

public class Employee  {
    private String empId;
    private String name;
    private String age;
    private String phoneNumber;
    private String joiningDate;
    private String designation;
    private byte[] image;
    private String emailId;
    private AttendanceDetails attendance;
    private PaymentDetails paymentDetails;
    private List<Vacation> vacations;
    private String status; // Hiring in progress, currently_working, fired;


    public Employee(String empId, String name, String age, String phoneNumber, String joiningDate, String designation, byte[] image, String emailId, AttendanceDetails attendance, PaymentDetails paymentDetails, List<Vacation> vacations, String status) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.image = image;
        this.emailId = emailId;
        this.attendance = attendance;
        this.paymentDetails = paymentDetails;
        this.vacations = vacations;
        this.status = status;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmployeeName() {
        return name;
    }

    public void setEmployeeName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public AttendanceDetails getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceDetails attendance) {
        this.attendance = attendance;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
