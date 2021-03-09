package com.example.bigbusiness.Main.ui.StaffMangement;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

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
    SearchView employee_searchview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_management);
        getSupportActionBar().hide();
        LinearLayout llRootView=findViewById(R.id.employee_SearchBar);
        llRootView.clearFocus();


    }

    @Override
    protected void onStart() {
        super.onStart();
//        LinearLayout llRootView=findViewById(R.id.employee_SearchBar);
//        llRootView.clearFocus();
        RecyclerView Employeelist =(RecyclerView)findViewById(R.id.Employee_list);
        employee_searchview =(SearchView)findViewById(R.id.employee_SearchBar);

        employee_searchview.setIconified(true);
        employee_searchview.clearFocus();
        employeeService = EmployeeService.getInstance();
        StaffAdapter adapter= new StaffAdapter(this,employeeService);
        EmployeeService employeeService=new EmployeeService();

        Employeelist.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        Employeelist.setLayoutManager(layoutManager);

        employee_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Employee> filteredemployee=new ArrayList<>();
                for(Employee items:employeeService.getItem()){
                    String j =query.toUpperCase();
                    if(items.getEmployeeName().toUpperCase().contains(j))
                    {
                        filteredemployee.add(items);
                    }
                };
                adapter.setEmployeeList(filteredemployee);
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Employee> filteredemployee=new ArrayList<>();
                for(Employee items:employeeService.getItem()){
                    String j =newText.toUpperCase();
                    if(items.getEmployeeName().toUpperCase().contains(j))
                    {
                        filteredemployee.add(items);
                    }
                };
                adapter.setEmployeeList(filteredemployee);

                return true;
            }
        });





    }
}
