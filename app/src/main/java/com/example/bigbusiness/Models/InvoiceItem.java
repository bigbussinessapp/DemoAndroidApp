package com.example.bigbusiness.Models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class InvoiceItem implements Serializable {
    String invoiceId;
    String invoiceName;
    BuyerDetails buyer;
    HashMap<String, InvoiceProduct> items;

    public InvoiceItem()
    {

    }
    public InvoiceItem(String invoiceName, BuyerDetails buyer, HashMap<String, InvoiceProduct> items){
        this.invoiceName = invoiceName;
        this.buyer = buyer;
        this.items = items;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public BuyerDetails getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerDetails buyer) {
        this.buyer = buyer;
    }

    public void setItems(HashMap<String, InvoiceProduct> items) {
        this.items = items;
    }

    public HashMap<String, InvoiceProduct> getItems() {
        return items;
    }
}