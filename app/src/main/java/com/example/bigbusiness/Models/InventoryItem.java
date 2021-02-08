package com.example.bigbusiness.Models;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    static int id=0;

    int item_ID;
    String name;
    String unit;
    int quantity;
    int price;
    int totalprice;
    String invoiceId;

    public InventoryItem(int id, String name, int quantity, String unit, int price, String invoiceId){
//        this.item_ID = id ++;
        this.item_ID = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.totalprice = price * quantity;
        this.invoiceId = invoiceId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemID() {
        return this.item_ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;

    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInvoiceId()
    {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
    }
//
//    public String getTotalprice() {
//        return totalprice;
//    }
//
//    public void setTotalprice(String totalprice) {
//        this.totalprice = totalprice;
//    }
}
