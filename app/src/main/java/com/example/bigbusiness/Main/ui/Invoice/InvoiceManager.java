package com.example.bigbusiness.Main.ui.Invoice;

import com.example.bigbusiness.Main.ui.Inventory.InventoryDBHelper;
import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.Models.InvoiceItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InvoiceManager {

    LinkedHashMap<String, InvoiceItem> invoiceItems = new LinkedHashMap<>();
    InvoiceDBHelper invoiceDBHelper;
    public InvoiceManager(InvoiceDBHelper invoiceDBHelper) {
        this.invoiceDBHelper = invoiceDBHelper;
        addItem(new InvoiceItem(1, "prod1"));
        addItem(new InvoiceItem(2,"prod2"));
        addItem(new InvoiceItem(3, "prod3"));
    }

    public void addItem(InvoiceItem item)
    {
        if(this.invoiceDBHelper.addItem(item))
        {
            String success = "yeahh";
        }
    }

    public ArrayList<InvoiceItem> getInvoiceItems() {
        return this.invoiceDBHelper.getAllItems();
    }
}
