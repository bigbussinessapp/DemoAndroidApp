package com.example.bigbusiness.Main.ui.StaffMangement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.Employee;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.EmployeeService;

import java.util.ArrayList;

public class StaffActivity extends AppCompatActivity {
    EmployeeService employeeService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_management);
        getSupportActionBar().hide();
        RecyclerView Employeelist =(RecyclerView)findViewById(R.id.Employee_list);
        Button paySalary =(Button)findViewById(R.id.pay_salary);
        employeeService = EmployeeService.getInstance();
        StaffAdapter adapter= new StaffAdapter(this,employeeService);

        Employeelist.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        Employeelist.setLayoutManager(layoutManager);

//        paySalary.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i =  new Intent(StaffActivity.this,payroll_activity.class);
//                startActivity(i);
//            }
//        });
    }



}
