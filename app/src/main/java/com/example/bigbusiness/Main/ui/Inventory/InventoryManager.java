package com.example.bigbusiness.Main.ui.Inventory;

import android.content.ClipData;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.bigbusiness.Models.InventoryItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InventoryManager {
        LinkedHashMap<String, InventoryItem> inventoryItems = new LinkedHashMap<>();
        InventoryDBHelper inventoryDBHelper;
        InventoryFBHelper inventoryFBHelper;

        public InventoryManager(InventoryDBHelper inventoryDBHelper)
        {
            this.inventoryDBHelper = inventoryDBHelper;
//            addItem(new InventoryItem(1, "prod1", 20, "ml", 200, "1000",null));
  //          addItem(new InventoryItem(2,"prod2",100, "ml", 15, "1001",null));
    //        addItem(new InventoryItem(3, "prod3",20, "kgs", 50, "1002",null));
        }

        public InventoryManager(InventoryFBHelper inventoryFBHelper)
        {
            this.inventoryFBHelper = inventoryFBHelper;
        }

        public void addItem(InventoryItem item)
        {
              this.inventoryFBHelper.addItem(item);
//            if(this.inventoryFBHelper.addItem(item))
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

        public void deleteItem(InventoryItem item)
        {
            InventoryItem success = this.inventoryFBHelper.deleteItem(item);

//            if(success)
//            {
//                success =! success;
//                return;
//            }
            //return removedItem;
        }

        public void editItem(InventoryItem item){
            boolean success = this.inventoryFBHelper.updateItem(item);
            if(success)
            {
                success =! success;
                return;
            }
        }

        public ArrayList<InventoryItem> getInventoryItems() {
            return this.inventoryFBHelper.getItems();
        }
    }
