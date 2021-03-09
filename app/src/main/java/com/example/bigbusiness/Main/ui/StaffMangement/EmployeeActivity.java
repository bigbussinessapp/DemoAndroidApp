package com.example.bigbusiness.Main.ui.StaffMangement;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bigbusiness.R;

import java.io.ObjectInputStream;

public class EmployeeActivity extends AppCompatActivity {

    Context context;
    String[] elistArray = {"CHECK CALENDER","PAY SALARY","LEAVE REQUEST","GIVE HIKE","SEND A MESSAGE","FIRE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        getSupportActionBar().hide();



        TextView employeeName = (TextView)findViewById(R.id.employee_name);
        TextView salary = (TextView)findViewById(R.id.employee_salary);
        TextView LeavesRemaining = (TextView)findViewById(R.id.leaves_remaining);
        TextView LastPaidOn = (TextView)findViewById(R.id.last_paid);

        //Button paysalary = (Button)findViewById(R.id.pay_salary);
        // ArrayAdapter adapter = new ArrayAdapter<String>(this,
        // R.layout.employee_details_list, elistArray);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.employee_details_list,elistArray){

            public View getView(int position, View convertView, ViewGroup parent){
                View view=super.getView(position,convertView,parent);
                TextView tv=(TextView)view.findViewById(R.id.label);
                if(position==elistArray.length-1){
                    tv.setTextColor(Color.RED);
                }
                else{
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }

        };
        ListView listView = (ListView) findViewById(R.id.edlist);
        listView.setAdapter(adapter);


        Bundle bundle=getIntent().getExtras();

        if(bundle.getString("employeename")!= null)
        {
            String j =(String) bundle.get("employeename");
            employeeName.setText(j);
        }
        if(bundle.getString("Salary")!= null)
        {
            String j =(String) bundle.get("Salary");
            salary.setText(j);
        }
        if(bundle.getString("LeavesRemaining")!= null)
        {
            String j =(String) bundle.get("LeavesRemaining");
            LeavesRemaining.setText(j);
        }
        if(bundle.getString("LastpaidOn")!= null)
        {
            String j =(String) bundle.get("LastpaidOn");
            LastPaidOn.setText(j);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private ObjectInputStream.GetField employeeList;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                String str=(String)o;
                if (str=="PAY SALARY"){
                    //Toast.makeText(getBaseContext(),"pay salary",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(EmployeeActivity.this,payroll_activity.class);
                    i.putExtra("bundle",bundle);
                    i.putExtra("employeename",bundle.get("employeename").toString());
                    i.putExtra("base",bundle.get("base").toString());
                    i.putExtra("incentives",bundle.get("incentives").toString());
                    i.putExtra("penalties",bundle.get("penalties").toString());
                    i.putExtra("providentfund",bundle.get("providentfund").toString());
                    i.putExtra("lastmonthdues",bundle.get("lastmonthdues").toString());
                    i.putExtra("Advancepayment",bundle.get("Advancepayment").toString());
                    startActivity(i);

                }
                else{
                    Toast.makeText(getBaseContext(),str,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}