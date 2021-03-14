package com.example.bigbusiness.Models;

import com.example.bigbusiness.Main.ui.Inventory.InventoryManager;

import java.io.Serializable;

public class InvoiceProduct implements Serializable {
    String productCode;
    String quantity;
    String name;
    InventoryManager inventoryManager;

    public InvoiceProduct()
    {

    }

    public InvoiceProduct(String productCode, String quantity){
        inventoryManager = InventoryManager.getInstance();
        this.productCode = productCode;
        this.quantity = quantity;
        this.name = getName(productCode);
    }

    public String getName(String productId)
    {
        return inventoryManager.getItemById(productId).getName();
    }

    public String getProductCode() {
        return productCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}