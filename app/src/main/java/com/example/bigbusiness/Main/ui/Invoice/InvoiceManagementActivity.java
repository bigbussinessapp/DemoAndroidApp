package com.example.bigbusiness.Main.ui.Invoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bigbusiness.Main.ui.Inventory.InventoryManager;
import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.Models.User;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InvoiceManagementActivity extends AppCompatActivity {
    ImageView addInvoice;
    RecyclerView invoiceListView;
    FirebaseDatabase database;
    DatabaseReference reference;
    UserDataService userDataService;
    InventoryManager inventoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_management);
        userDataService = UserDataService.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Users").child(userDataService.getLoggedInUser().getUid()).child("Invoices");
        inventoryManager = InventoryManager.getInstance();

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

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<InvoiceItem> list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    InvoiceItem invoice = dataSnapshot.getValue(InvoiceItem.class);
                    list.add(invoice);
                }
                invoiceListAdapter.setInventoryItemsList(list);
                invoiceListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        this.addInvoice = (ImageView) findViewById(R.id.addinvoice);
        this.addInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDialog();
                Intent i = new Intent(InvoiceManagementActivity.this, AddInvoiceActivity.class);
                startActivity(i);
            }
        });
    }
}
