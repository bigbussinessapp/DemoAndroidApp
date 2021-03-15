package com.example.bigbusiness.Main.ui.Invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bigbusiness.Main.HomeActivity;
import com.example.bigbusiness.Main.ui.Inventory.NewAddInventoryActivity;
import com.example.bigbusiness.Main.ui.Profile.NewProfileActivity;
import com.example.bigbusiness.Models.BuyerDetails;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.R;

import java.util.ArrayList;
import java.util.List;

public class NewInvoiceManagementActivity extends AppCompatActivity {
    ImageView addInvoice;
    RecyclerView invoiceListView;
    List<InvoiceItem> invoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_invoice_management);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        invoices = new ArrayList<>();

        TextView titleBtn;
        ImageView profile;
        titleBtn = findViewById(R.id.titleHeading);
        profile = findViewById(R.id.profileIcon);
        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewInvoiceManagementActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewInvoiceManagementActivity.this, NewProfileActivity.class);
                startActivity(i);
            }
        });

        InvoiceItem dummyInvoice1 = new InvoiceItem("Invoice 1", new BuyerDetails("Bharath"), null, null);
        InvoiceItem dummyInvoice2 = new InvoiceItem("Invoice 2", new BuyerDetails("Aryan"), null, null);
        invoices.add(dummyInvoice1);
        invoices.add(dummyInvoice2);

        invoiceListView = findViewById(R.id.new_invoice_list);
        NewInvoiceListAdapter invoiceListAdapter = new NewInvoiceListAdapter(this, invoices);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        invoiceListView.setLayoutManager(layoutManager);
        invoiceListView.setAdapter(invoiceListAdapter);
    }
}