package com.example.bigbusiness.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bigbusiness.Main.ui.Finance.FinanceActivity;
import com.example.bigbusiness.Main.ui.Inventory.InventoryActivity;
import com.example.bigbusiness.Main.ui.Inventory.NewInventoryActivity;
import com.example.bigbusiness.Main.ui.Invoice.NewInvoiceManagementActivity;
import com.example.bigbusiness.Main.ui.Profile.NewProfileActivity;
import com.example.bigbusiness.R;

public class HomeActivity extends AppCompatActivity {
    Button invoiceBtn, inventoryBtn, staffManagementBtn, financeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_mainactivity);
        inventoryBtn = findViewById(R.id.inventory_btn_home);
        invoiceBtn = findViewById(R.id.invoice_btn_home);
        financeBtn = findViewById(R.id.finance_btn_home);

        TextView titleBtn;
        ImageView profile;
        titleBtn = findViewById(R.id.titleHeading);
        profile = findViewById(R.id.profileIcon);
        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, NewProfileActivity.class);
                startActivity(i);
            }
        });

        invoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, NewInvoiceManagementActivity.class);
                startActivity(i);
            }
        });

        inventoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, NewInventoryActivity.class);
                startActivity(i);
            }
        });

        financeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FinanceActivity.class);
                startActivity(i);
            }
        });
    }
}