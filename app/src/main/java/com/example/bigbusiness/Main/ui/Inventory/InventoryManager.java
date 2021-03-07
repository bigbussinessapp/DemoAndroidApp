package com.example.bigbusiness.Main.ui.Inventory;

import android.widget.Toast;

import com.example.bigbusiness.Main.ui.Invoice.InvoiceManager;
import com.example.bigbusiness.Models.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class InventoryManager {
    List<InventoryItem> inventoryItems = new ArrayList<>();
    InventoryDBHelper inventoryDBHelper;
    private static InventoryManager instance = null;

    public InventoryManager()
    {
        this.inventoryDBHelper = inventoryDBHelper;
        addItem(new InventoryItem(1, "prod1", 20, "ml", 200, "1000",null));
        addItem(new InventoryItem(2,"prod2",100, "ml", 15, "1001",null));
        addItem(new InventoryItem(3, "prod3",20, "kgs", 50, "1002",null));
    }

    public static InventoryManager getInstance()
    {
        if(instance == null)
        {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addItem(InventoryItem item)
    {
        inventoryItems.add(item);
//            if(this.inventoryDBHelper.addItem(item))
//            {
//                String success = "yeahh";
//            }
    }

    public void increaseQuantity(InventoryItem item)
    {
        this.inventoryDBHelper.increaseQuantity(item);
    }

    public void decreaseQuantity(InventoryItem item)
    {
        this.inventoryDBHelper.decreaseQuantity(item);
    }

    public void updateItemWithQuantity(String item_id, String quantity)
    {
        for (InventoryItem i : inventoryItems)
        {
            if(i.getItemID() == Integer.parseInt(item_id))
            {
                i.setQuantity(Integer.parseInt(quantity));
            }
        }
    }

    public void deleteItem(InventoryItem item)
    {
        boolean success = this.inventoryDBHelper.deleteItem(item);

        if(success)
        {
            success =! success;
            return;
        }
        //return removedItem;
    }

    public void editItem(InventoryItem item){
        boolean success = this.inventoryDBHelper.updateItem(item);
        if(success)
        {
            success =! success;
            return;
        }
    }

    public InventoryItem getItemById(String productId)
    {
        for(InventoryItem i: getInventoryItems())
        {
            if(String.valueOf(i.getItemID()).equals(productId))
            {
                return i;
            }
        }
        return null;
    }

    public List<InventoryItem> getInventoryItems() {
//            return this.inventoryDBHelper.getAllItems();
        return inventoryItems;
    }

    public void updateItems(HashMap<String, String> productsWithQuantity) {
        for(String id : productsWithQuantity.keySet())
        {
            updateItemWithQuantity(id, productsWithQuantity.get(id));
        }
    }
}
