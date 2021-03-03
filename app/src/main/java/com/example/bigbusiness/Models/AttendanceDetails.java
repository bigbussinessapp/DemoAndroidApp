package com.example.bigbusiness.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendanceDetails {


    private String compulsoryWorkingHrs;
    private HashMap<String, Attendance> attendanceDetails; // key: date(22/01/2021)
    private ArrayList<String> publicHolidays = new ArrayList<>();// Saturday, Sunday, Festivals


// TO DO : REMOVE FIRST PARAMETER

    public AttendanceDetails(String compulsoryWorkingHrs, HashMap<String, Attendance> attendanceDetails) {
        this.compulsoryWorkingHrs = compulsoryWorkingHrs;
        this.attendanceDetails = attendanceDetails;
    }
    public AttendanceDetails(String compulsoryWorkingHrs, HashMap<String, Attendance> attendanceDetails, List<String> publicHoliday) {
        publicHolidays.add("10");
        publicHolidays.add("20");
        publicHolidays.add("30");
        publicHolidays.add("40");
        this.compulsoryWorkingHrs = compulsoryWorkingHrs;
        this.attendanceDetails = attendanceDetails;
        //this.publicHolidays = publicHolidays;
    }
//    logInUser(loggedInTime);
//    logOutUser(logoutTime){
//        attendance = new Attendance(loggedInTime, logoutTime);
//        addAttendance(attendance);
//    }


    public String getCompulsoryWorkingHrs() {
        return compulsoryWorkingHrs;
    }

    public void setCompulsoryWorkingHrs(String compulsoryWorkingHrs) {
        this.compulsoryWorkingHrs = compulsoryWorkingHrs;
    }

    public HashMap<String, Attendance> getAttendanceDetails() {
        return attendanceDetails;
    }

    public void setAttendanceDetails(HashMap<String, Attendance> attendanceDetails) {
        this.attendanceDetails = attendanceDetails;
    }

    public List<String> getPublicHolidays() {
        return publicHolidays;
    }

//    public void setPublicHolidays(List<String> publicHolidays) {
//        this.publicHolidays = publicHolidays;
//    }
}
