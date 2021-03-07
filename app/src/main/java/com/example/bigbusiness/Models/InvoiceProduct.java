package com.example.bigbusiness.Models;

import com.example.bigbusiness.Main.ui.Inventory.InventoryManager;

import java.io.Serializable;

public class InvoiceProduct implements Serializable {
    String productId;
    String quantity;
    InventoryManager inventoryManager;

    public InvoiceProduct(String productId, String quantity){
        this.productId = productId;
        this.quantity = quantity;
        inventoryManager = InventoryManager.getInstance();
    }

    public String getName(String productId)
    {
        return inventoryManager.getItemById(productId).getName();
    }

    public String getProductId() {
        return productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}