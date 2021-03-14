package com.example.bigbusiness.Main.ui.Services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigbusiness.Main.ui.Invoice.InvoiceManagementActivity;
import com.example.bigbusiness.R;

public class ServicesActivity extends AppCompatActivity {
   private  Button gstBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        gstBtn = findViewById(R.id.gst_service);

        gstBtn.setOnClickListener(gstServiceClick);

    }
    private View.OnClickListener gstServiceClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ServicesActivity.this, GSTServiceActivity.class);
                    startActivity(i);
                }
            };


}
