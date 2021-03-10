package com.example.bigbusiness.Main.ui.Invoice;

import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.Services.UserDataService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InvoiceFBHelper {

    FirebaseDatabase database;
    DatabaseReference reference;
    UserDataService userDataService;
    private static InvoiceFBHelper instance;
    private InvoiceFBHelper()
    {
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

    public void addInvoice(InvoiceItem invoice)
    {
        String key = reference.push().getKey();
        invoice.setInvoiceId(key);
        reference.child(key).setValue(invoice);
    }

    public void editInvoice(InvoiceItem invoice)
    {

    }
}
