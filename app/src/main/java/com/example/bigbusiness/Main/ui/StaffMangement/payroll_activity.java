package com.example.bigbusiness.Main.ui.StaffMangement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bigbusiness.Models.Employee;
import com.example.bigbusiness.R;

public class payroll_activity extends AppCompatActivity {
    private double Total=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll);
        getSupportActionBar().hide();

        TextView employeename = (TextView)findViewById(R.id.employee_name);
        TextView base =(TextView)findViewById(R.id.employee_base);
        TextView incentives =(TextView)findViewById(R.id.employee_incentives);
        TextView penalties =(TextView)findViewById(R.id.employee_penalties);
        TextView providentfund =(TextView)findViewById(R.id.employee_pf);
        TextView lastmonthdues =(TextView)findViewById(R.id.employee_lastmonth_dues);
        TextView Advancepayment =(TextView)findViewById(R.id.employee_advance_pay);
        TextView total = (TextView)findViewById(R.id.employee_total);


        Bundle bundle=this.getIntent().getExtras();

        if(bundle.getString("employeename")!= null)
        {
            String j =(String) bundle.get("employeename");
            employeename.setText(j);
        }
        if(bundle.getString("base")!= null)
        {
            String j =(String) bundle.get("base");
            Total +=Double.parseDouble(j);
            base.setText(j);
        }
        if(bundle.getString("incentives")!= null)
        {
            String j =(String) bundle.get("incentives");
            Total+= Double.parseDouble(j);
            incentives.setText(j);

        }
        if(bundle.getString("penalties")!= null)
        {
            String j =(String) bundle.get("penalties");
            Total-= Double.parseDouble(j);
            penalties.setText(j);

        }
        if(bundle.getString("providentfund")!= null)
        {
            String j =(String) bundle.get("providentfund");
            Total-= Double.parseDouble(j);
            providentfund.setText(j);

        }
        if(bundle.getString("lastmonthdues")!= null)
        {
            String j =(String) bundle.get("lastmonthdues");
            Total+= Double.parseDouble(j);

            lastmonthdues.setText(j);

        }
        if(bundle.getString("Advancepayment")!= null)
        {
            String j =(String) bundle.get("Advancepayment");
            Total-= Double.parseDouble(j);
            Advancepayment.setText(j);

        }

        total.setText(String.valueOf(Total));


    }
}