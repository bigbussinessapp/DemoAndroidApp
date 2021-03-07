package com.example.bigbusiness.Models;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    static int id=0;

    int item_ID;
    String name;
    String unit;
    String uom;
    int quantity;
    int price;
    int totalprice;
    String invoiceId;
    byte[] image;

    public InventoryItem(String name, String quantity, String unit, String uom, String price, String invoiceId){
//        this.item_ID = id ++;
        this.item_ID = id;
        this.name = name;
        this.quantity = Integer.parseInt(quantity);
        this.unit = unit;
        this.uom = uom;
        this.price = Integer.parseInt(price);
        this.totalprice = this.price * this.quantity;
        this.invoiceId = invoiceId;
        this.image = image;
    }

    public InventoryItem(int id, String name, int quantity, String unit, int price, String invoiceId,byte[] image){
//        this.item_ID = id ++;
        this.item_ID = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.totalprice = price * quantity;
        this.invoiceId = invoiceId;
//        this.image = image;
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

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public String getInvoiceId()
    {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
