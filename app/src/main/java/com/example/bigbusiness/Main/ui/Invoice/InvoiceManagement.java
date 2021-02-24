package com.example.bigbusiness.Main.ui.Invoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bigbusiness.Main.ui.Inventory.AddInventoryItem;
import com.example.bigbusiness.Main.ui.Inventory.InventoryActivity;
import com.example.bigbusiness.Main.ui.Inventory.InventoryDBHelper;
import com.example.bigbusiness.Main.ui.Inventory.InventoryListAdapter;
import com.example.bigbusiness.Main.ui.Inventory.InventoryManager;
import com.example.bigbusiness.R;

public class InvoiceManagement extends AppCompatActivity {
    Button addInvoice;
    RecyclerView invoiceListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_management);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    protected void onStart() {
        super.onStart();

        invoiceListView = findViewById(R.id.invoice_list);
        InvoiceDBHelper invoiceDBHelper = new InvoiceDBHelper(this);
        InvoiceManager invoiceManager = new InvoiceManager(invoiceDBHelper);
        InvoiceListAdapter invoiceListAdapter = new InvoiceListAdapter(this, this, invoiceManager);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1 , StaggeredGridLayoutManager.VERTICAL);
        invoiceListView.setLayoutManager(layoutManager);
        invoiceListView.setAdapter(invoiceListAdapter);


        this.addInvoice = (Button) findViewById(R.id.addinvoice);
        this.addInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }

            private void openDialog() {
                InvoiceDialog invoicedialog = new InvoiceDialog();
                invoicedialog.show(getSupportFragmentManager(),"invoice dialog");
            }
        });
    }
}
