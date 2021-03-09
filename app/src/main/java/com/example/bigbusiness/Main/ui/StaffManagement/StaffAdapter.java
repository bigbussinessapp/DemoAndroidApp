package com.example.bigbusiness.Main.ui.StaffMangement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.Employee;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.EmployeeService;

import java.util.List;


public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.viewHolder>{

    Context context;
    List<Employee> employeeList;
    EmployeeService employeeService;



    public StaffAdapter(Context context,  EmployeeService employeeService) {
        this.context = context;
        this.employeeList=employeeService.getItem();
        this.employeeService = employeeService;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_list_card,parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Employee cardClicked = this.employeeList.get(position);

        holder.employeeName.setText(cardClicked.getEmployeeName());
        holder.employeeloginlogout.setText(cardClicked.getJoiningDate());


    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView employeeName,employeeloginlogout;
        Button paySalary;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            employeeName = itemView.findViewById(R.id.employee_name);
            employeeloginlogout = itemView.findViewById(R.id.employee_login_logout);
            paySalary = itemView.findViewById(R.id.pay_salary);

        }
    }
}
