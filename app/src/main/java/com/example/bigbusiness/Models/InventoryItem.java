package com.example.bigbusiness.Models;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    static int id=0;

    String itemCode;
    String name;
    String unit;
    String uom;
    int quantity;
    int price;
    int totalprice;
    byte[] image;

    //For firebase purpose
    public InventoryItem()
    {

    }

    public InventoryItem(String id, String name, String quantity, String unit, String uom, String price, byte[] image){
        this.itemCode = id;
        this.name = name;
        this.quantity = Integer.parseInt(quantity);
        this.unit = unit;
        this.uom = uom;
        this.price = Integer.parseInt(price);
        this.totalprice = this.price * this.quantity;
        this.image = image;
    }

    public InventoryItem(String id, String name, int quantity, String unit, int price, String invoiceId, byte[] image){
//        this.item_ID = id ++;
        this.itemCode = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.totalprice = price * quantity;
//        this.image = image;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemCode() {
        return this.itemCode;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
