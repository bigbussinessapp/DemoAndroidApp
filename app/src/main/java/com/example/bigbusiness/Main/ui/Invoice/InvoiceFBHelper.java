package com.example.bigbusiness.Main.ui.Invoice;

import androidx.annotation.NonNull;

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.Services.UserDataService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InvoiceFBHelper {
    List<InvoiceItem> invoices;
    FirebaseDatabase database;
    DatabaseReference reference;
    UserDataService userDataService;
    private static InvoiceFBHelper instance;
    private InvoiceFBHelper()
    {
        invoices = new ArrayList<>();
        userDataService = UserDataService.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Invoices");
    }

    public static InvoiceFBHelper getInstance()
    {
        if(instance == null)
        {
            instance = new InvoiceFBHelper();
        }
        return instance;
    }

    public List<InvoiceItem> getInvoices()
    {
        return invoices;
    }

    public void addInvoice(InvoiceItem invoice)
    {
        String key = reference.push().getKey();
        invoice.setInvoiceId(key);
        reference.child(key).setValue(invoice);
    }

    public void editInvoice(InvoiceItem invoice)
    {

    }

    public void refresh() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                invoices.clear();
                for(DataSnapshot item : snapshot.getChildren())
                {
                    invoices.add(item.getValue(InvoiceItem.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
