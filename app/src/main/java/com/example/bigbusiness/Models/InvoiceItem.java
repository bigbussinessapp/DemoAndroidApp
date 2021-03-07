package com.example.bigbusiness.Models;

import java.io.Serializable;
import java.util.List;

public class InvoiceItem implements Serializable {
    String invoiceId;
    String invoiceName;
    BuyerDetails buyer;
    List<InvoiceProduct> items;

    public InvoiceItem(String invoiceName, BuyerDetails buyer, List<InvoiceProduct> items){
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

    public void setItems(List<InvoiceProduct> items) {
        this.items = items;
    }

    public List<InvoiceProduct> getItems() {
        return items;
    }
}