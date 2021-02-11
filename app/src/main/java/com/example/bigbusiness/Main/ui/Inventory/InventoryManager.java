package com.example.bigbusiness.Main.ui.Inventory;

import android.widget.Toast;

import com.example.bigbusiness.Models.InventoryItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InventoryManager {
        LinkedHashMap<String, InventoryItem> inventoryItems = new LinkedHashMap<>();
        InventoryDBHelper inventoryDBHelper;

        public InventoryManager(InventoryDBHelper inventoryDBHelper)
        {
            this.inventoryDBHelper = inventoryDBHelper;
//            addItem(new InventoryItem(1, "prod1", 20, "ml", 200, "1000",null));
  //          addItem(new InventoryItem(2,"prod2",100, "ml", 15, "1001",null));
    //        addItem(new InventoryItem(3, "prod3",20, "kgs", 50, "1002",null));
        }

        public void addItem(InventoryItem item)
        {
            if(this.inventoryDBHelper.addItem(item))
            {
                String success = "yeahh";
            }
        }

        public void increaseQuantity(InventoryItem item)
        {
            this.inventoryDBHelper.increaseQuantity(item);
        }

        public void decreaseQuantity(InventoryItem item)
        {
            this.inventoryDBHelper.decreaseQuantity(item);
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

        public ArrayList<InventoryItem> getInventoryItems() {
            return this.inventoryDBHelper.getAllItems();
        }
    }
