package com.example.bigbusiness.Main.ui.Inventory;

import com.example.bigbusiness.Models.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryManager {
    List<InventoryItem> inventoryItems = new ArrayList<>();
//    InventoryDBHelper inventoryDBHelper;
    private static InventoryManager instance = null;
    InventoryFBHelper inventoryFBHelper;

    public InventoryManager()
    {
        this.inventoryFBHelper = InventoryFBHelper.getInstance();
//        this.inventoryDBHelper = inventoryDBHelper;
//        addItem(new InventoryItem(1, "prod1", 20, "ml", 200, "1000",));
//        addItem(new InventoryItem(2,"prod2",100, "ml", 15, "1001",null));
//        addItem(new InventoryItem(3, "prod3",20, "kgs", 50, "1002",null));

//        addItem(new InventoryItem("100000", "Eye drops", "20", "20", "ml", "200",null));
//        addItem(new InventoryItem("200000","Coconut Oil","100", "200", "ml", "15", null));
//        addItem(new InventoryItem("300000", "Rice","200", "5", "kg", "50", null));
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
        this.inventoryFBHelper.addItem(item);
        inventoryItems.add(item);
//            if(this.inventoryDBHelper.addItem(item))
//            {
//                String success = "yeahh";
//            }
    }

//    public void increaseQuantity(InventoryItem item)
//    {
//        this.inventoryDBHelper.increaseQuantity(item);
//    }
//
//    public void decreaseQuantity(InventoryItem item)
//    {
//        this.inventoryDBHelper.decreaseQuantity(item);
//    }

    public void updateItemWithQuantity(String item_code, String quantity)
    {
        for (InventoryItem i : inventoryItems)
        {
            if(i.getItemCode().equals(item_code))
            {
                i.setQuantity(Integer.parseInt(quantity));
            }
        }
    }

    public void deleteItem(InventoryItem item)
    {
        this.inventoryFBHelper.deleteItem(item);
//        boolean success = this.inventoryDBHelper.deleteItem(item);
//
//        if(success)
//        {
//            success =! success;
//            return;
//        }
        //return removedItem;
    }

    public void updateItem(InventoryItem item){
        this.inventoryFBHelper.updateItem(item);
//        boolean success = this.inventoryDBHelper.updateItem(item);
//        if(success)
//        {
//            success =! success;
//            return;
//        }
    }

    public InventoryItem getItemById(String productId)
    {
        for(InventoryItem i: getInventoryItems())
        {
            if(String.valueOf(i.getItemCode()).equals(productId))
            {
                return i;
            }
        }
        return null;
    }

    public List<InventoryItem> getInventoryItems() {
        return this.inventoryFBHelper.getAllItems();
    }

    public void updateItems(HashMap<String, String> productsWithQuantity) {
        for(String id : productsWithQuantity.keySet())
        {
            updateItemWithQuantity(id, productsWithQuantity.get(id));
        }
    }

    public void setItems(List<InventoryItem> inventoryItemsList) {
        this.inventoryItems = inventoryItemsList;
    }

    public void refreshInventoryItems() {
        inventoryFBHelper.refresh();
    }

}
