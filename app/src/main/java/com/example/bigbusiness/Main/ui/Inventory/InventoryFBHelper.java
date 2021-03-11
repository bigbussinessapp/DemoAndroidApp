package com.example.bigbusiness.Main.ui.Inventory;

import androidx.annotation.NonNull;

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.Services.UserDataService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InventoryFBHelper {
    List<InventoryItem> items = new ArrayList<InventoryItem>();
    private static InventoryFBHelper instance;
    FirebaseDatabase database;
    DatabaseReference reference, globalReference;
    UserDataService userDataService;

    public InventoryFBHelper(){
        userDataService = UserDataService.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Inventory");
        globalReference = database.getReference("GlobalInventory");
//        Items.add(new InventoryItem(1, "Demo1" , 10 , "2" , 5000 , "invoiceID", null));
//        Items.add(new InventoryItem(2, "Demo2" , 15 , "3" , 7000 , "invoiceID", null ));
    }

    public void addItem(InventoryItem item){
        reference.child(item.getItemCode()).setValue(item);
        globalReference.child(item.getItemCode()).setValue(item);
        items.add(item);

//        Items.add(Item);
    }

//    public InventoryItem createItem( String name ,int quantity, String unit, int price, String invoiceId){
//        String id  = 1110 + getItems().size() + 1 ;//getUniqueID();
//        InventoryItem item = new InventoryItem( id , name , quantity , unit , price , invoiceId, null );
//        return item;
//    }

    public List<InventoryItem> getAllItems()
    {
        return items;
    }

    public static InventoryFBHelper getInstance()
    {
        if(instance == null)
        {
            instance = new InventoryFBHelper();
        }
        return instance;
    }

    public void deleteItem(InventoryItem item)
    {
        reference.child(item.getItemCode()).removeValue();
        for(int i=0; i < items.size(); i++)
        {
            if(items.get(i).getItemCode().equals(item.getItemCode()))
            {
                items.remove(i);
                break;
            }
        }
//        int index = getIndex(Item);
//        if(index == -1)
//            return null;
//        InventoryItem deleteItem = Items.remove(index);
//        return deleteItem;
    }


    public int getIndex(InventoryItem Item)
    {
        int index = -1;
        for(int i = 0; i< items.size(); i++)
        {
            if(items.get(i).getItemCode() == Item.getItemCode())
            {
                index = i;
                break;
            }
        }
        return index;
    }

    public void updateItem(InventoryItem item)
    {
        reference.child(item.getItemCode()).setValue(item);
        for(int i=0; i < items.size(); i++)
        {
            if(items.get(i).getItemCode().equals(item.getItemCode()))
            {
                items.add(i, item);
                break;
            }
        }
//        int index = getIndex(Item);
//        Items.remove(Item);
//        Items.add(index, Item);
//        return true;
    }

    public void refresh() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for(DataSnapshot item : snapshot.getChildren())
                {
                    items.add(item.getValue(InventoryItem.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
