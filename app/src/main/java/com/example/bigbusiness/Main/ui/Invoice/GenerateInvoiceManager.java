package com.example.bigbusiness.Main.ui.Invoice;

import com.example.bigbusiness.Models.GenerateInvoiceItem;
import com.example.bigbusiness.Models.InvoiceItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GenerateInvoiceManager {

    LinkedHashMap<String, GenerateInvoiceItem> invoiceItems = new LinkedHashMap<>();
    GenerateInvoiceDBHelper invoiceDBHelper;

    public GenerateInvoiceManager(GenerateInvoiceDBHelper invoiceDBHelper) {
        this.invoiceDBHelper = invoiceDBHelper;
        addItem(new GenerateInvoiceItem(1, "prod1","ddc","vhj","cdc"));
        addItem(new GenerateInvoiceItem(1, "prod1","ddc","vhj","cdc"));

    }
        public void addItem (GenerateInvoiceItem item)
        {
            if (this.invoiceDBHelper.addItem(item)) {
                String success = "yeahh";
            }
        }

        public ArrayList<GenerateInvoiceItem> getInvoiceItems () {
            return this.invoiceDBHelper.getAllItems();
        }
    }
