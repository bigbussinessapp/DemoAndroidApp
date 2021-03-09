package com.example.bigbusiness.Main.ui.Invoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bigbusiness.R;

public class InvoiceManagementActivity extends AppCompatActivity {
    ImageView addInvoice;
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
        InvoiceManager invoiceManager = InvoiceManager.getInstance();
        InvoiceListAdapter invoiceListAdapter = new InvoiceListAdapter(this, this, invoiceManager);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1 , StaggeredGridLayoutManager.VERTICAL);
        invoiceListView.setLayoutManager(layoutManager);
        invoiceListView.setAdapter(invoiceListAdapter);


        this.addInvoice = (ImageView) findViewById(R.id.addinvoice);
        this.addInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDialog();
                Intent i = new Intent(InvoiceManagementActivity.this, AddInvoiceActivity.class);
                startActivity(i);
            }

//            private void openDialog() {
//                InvoiceDialog invoicedialog = new InvoiceDialog();
//                invoicedialog.show(getSupportFragmentManager(),"invoice dialog");
//            }
        });
    }
}
