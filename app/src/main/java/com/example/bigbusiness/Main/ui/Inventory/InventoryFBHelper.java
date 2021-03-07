package com.example.bigbusiness.Main.ui.Inventory;

import com.example.bigbusiness.Models.InventoryItem;

import java.util.ArrayList;

public class InventoryFBHelper {
    ArrayList<InventoryItem> Items = new ArrayList<InventoryItem>();
    private static InventoryFBHelper instance;

    public InventoryFBHelper(){
        Items.add(new InventoryItem(1, "Demo1" , 10 , "2" , 5000 , "invoiceID" ));
        Items.add(new InventoryItem(2, "Demo2" , 15 , "3" , 7000 , "invoiceID" ));
    }

    public void addItem(InventoryItem Item){
        Items.add(Item);
    }

    public InventoryItem createItem( String name ,int quantity, String unit, int price, String invoiceId){
        int id  = 1110 + getItems().size() + 1 ;//getUniqueID();
        InventoryItem item = new InventoryItem( id , name , quantity , unit , price , invoiceId );
        return item;
    }

    public ArrayList<InventoryItem> getItems()
    {
        return Items;
    }

    public static InventoryFBHelper getInstance()
    {
        if(instance == null)
        {
            instance = new InventoryFBHelper();
        }
        return instance;
    }

    public InventoryItem deleteItem(InventoryItem Item)
    {
        int index = getIndex(Item);
        if(index == -1)
            return null;
        InventoryItem deleteItem = Items.remove(index);
        return deleteItem;
    }


    public int getIndex(InventoryItem Item)
    {
        int index = -1;
        for(int i=0; i<Items.size(); i++)
        {
            if(Items.get(i).getItemID() == Item.getItemID())
            {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean updateItem(InventoryItem Item)
    {
        int index = getIndex(Item);
        Items.remove(Item);
        Items.add(index, Item);
        return true;
    }
}
