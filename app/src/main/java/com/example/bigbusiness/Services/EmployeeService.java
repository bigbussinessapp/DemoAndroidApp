
package com.example.bigbusiness.Services;

import com.example.bigbusiness.Models.Attendance;
import com.example.bigbusiness.Models.AttendanceDetails;
import com.example.bigbusiness.Models.BankDetails;
import com.example.bigbusiness.Models.Employee;
import com.example.bigbusiness.Models.EmployeeIncentives;
import com.example.bigbusiness.Models.EmployeePenalties;
import com.example.bigbusiness.Models.PaymentDetails;
import com.example.bigbusiness.Models.Vacation;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeService {
    private static EmployeeService instance;
    ArrayList<Employee> employee = new ArrayList<>();
    //HashMap<String, Employee> employees;
//    createEmployee(employee){
//        setEmpId(employee);
//        VacationService.addEmployee(empId);
//    }
//    logInUser(empId){
//        AttendanceService.logInUser(empId);
//    }
//    logOutUser(empId){
//        AttendanceService.logOutUser(empId);
//    }
    public static EmployeeService getInstance(){
        if (instance==null){
            instance = new EmployeeService();
        }
        return instance;
    }
    ArrayList<Vacation> vacations=new ArrayList<>();
    public EmployeeService() {
        String empId = "emp00001";
        Attendance a1 = new Attendance();
        a1.setStatus("Present");
        a1.setLoggedInTime("02:00");
        a1.setLoggedOutTime("13:00");
        HashMap<String, Attendance> dummy = new HashMap<>();
        Employee employee1 = new Employee(
                "emp0001",
                "charan",
                "21",
                "6309833542",
                "10/01/2020",
                "DEV",
                null,
                "boppesricharan@gmail.com",
                new AttendanceDetails("9", dummy),
                new PaymentDetails("1000", "2000", "100", "11/02/2020", "1500", "1000", "", "10", "200",
                        new EmployeeIncentives("100","good"),
                        new EmployeePenalties("100","late"),
                        new BankDetails("12337289", "638761283", "mandpeta", "boi", "charan")),
                vacations,"Curently_working");
        employee.add(employee1);
    }


    //dummy.add(empId, a1);
    // dummy.put(empId,a1);

    public  ArrayList<Employee>  getItem(){
        return employee;
    }


}

