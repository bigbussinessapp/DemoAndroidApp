package com.example.bigbusiness.Models;

public class Attendance {
    private String status; // present, paidLeave, sickLeave, unpaidLeave, paternityLeave, maternityLeave, halfDay
    private String workingHrs;//decimal string  ex=6.5
    private String loggedInTime;
    private String loggedOutTime;

    public Attendance(){

    }

    public Attendance(String status,  String loggedInTime, String loggedOutTime) {
        this.status = status;
        this.loggedInTime = loggedInTime;
        this.loggedOutTime = loggedOutTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoggedInTime() {
        return loggedInTime;
    }

    public void setLoggedInTime(String loggedInTime) {
        this.loggedInTime = loggedInTime;
    }

    public String getLoggedOutTime() {
        return loggedOutTime;
    }

    public void setLoggedOutTime(String loggedOutTime) {
        this.loggedOutTime = loggedOutTime;
    }
}
