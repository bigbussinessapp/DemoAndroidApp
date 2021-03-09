package com.example.bigbusiness.Main.ui.StaffMangement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.Employee;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.EmployeeService;

import java.util.ArrayList;
import java.util.List;


public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.viewHolder>{

    Context context;
    List<Employee> employeeList;
    EmployeeService employeeService;

    public StaffAdapter(Context context, EmployeeService employeeService) {
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
        holder.paySalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"Hi",Toast.LENGTH_LONG);
                Intent i =  new Intent(context,payroll_activity.class);
                //startActivity(i);
                i.putExtra("employeename",cardClicked.getEmployeeName());
                i.putExtra("base",cardClicked.getPaymentDetails().getBaseSalary());
                i.putExtra("incentives",cardClicked.getPaymentDetails().getIncentives().getAmount());
                i.putExtra("penalties",cardClicked.getPaymentDetails().getPenalties().getAmount());
                i.putExtra("providentfund",cardClicked.getPaymentDetails().getProvidentFund());
                i.putExtra("lastmonthdues",cardClicked.getPaymentDetails().getPaymentDue());
                i.putExtra("Advancepayment",cardClicked.getPaymentDetails().getAdvancePayment());
                context.startActivity(i);


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //v.getContext().startActivity(new Intent(v.getContext(),EmployeeActivity.class));
                Intent i =  new Intent(context,EmployeeActivity.class);
                i.putExtra("employeename",cardClicked.getEmployeeName());
                i.putExtra("Salary",cardClicked.getPaymentDetails().getCurr_salary());
                i.putExtra("leavesRemaining","NONE");
                i.putExtra("LastpaidOn",cardClicked.getPaymentDetails().getLastPaidOn());
                i.putExtra("base",cardClicked.getPaymentDetails().getBaseSalary());
                i.putExtra("incentives",cardClicked.getPaymentDetails().getIncentives().getAmount());
                i.putExtra("penalties",cardClicked.getPaymentDetails().getPenalties().getAmount());
                i.putExtra("providentfund",cardClicked.getPaymentDetails().getProvidentFund());
                i.putExtra("lastmonthdues",cardClicked.getPaymentDetails().getPaymentDue());
                i.putExtra("Advancepayment",cardClicked.getPaymentDetails().getAdvancePayment());

                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void setEmployeeList(ArrayList<Employee> updatedlist){
//        this.employeeList.clear();
//        this.employeeList.addAll(updatedlist);

        this.employeeList=updatedlist;
        this.notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private Context context;
        TextView employeeName,employeeloginlogout;
        Button paySalary;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            employeeName = itemView.findViewById(R.id.employee_name);
            employeeloginlogout = itemView.findViewById(R.id.employee_login_logout);
            paySalary = itemView.findViewById(R.id.employee_pay_salary);



        }
    }
}
