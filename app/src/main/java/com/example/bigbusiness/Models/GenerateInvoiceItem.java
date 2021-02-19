package com.example.bigbusiness.Models;

import java.io.Serializable;

public class GenerateInvoiceItem implements Serializable {
    static int id = 0;
    String name;
    String invoicename;
    String buyername;
    String quantity;
    String price;
    public GenerateInvoiceItem(int id, String name, String buyername, String invoicename, String price){
//        this.item_ID = id ++;
        this.id = id;
        this.name = name;
        //this.quantity = quantity;
        this.invoicename = invoicename;
        this.buyername = buyername;
        this.price = price;

    }


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        InvoiceItem.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoicename() {
        return invoicename;
    }

    public void setInvoicename(String invoicename) {
        this.invoicename = invoicename;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}