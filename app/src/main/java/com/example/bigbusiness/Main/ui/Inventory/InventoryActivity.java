package com.example.bigbusiness.Main.ui.Inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bigbusiness.Main.ui.Finance.RemindersAndDues.ReminderAdapter;
import com.example.bigbusiness.Main.ui.Finance.RemindersAndDues.RemindersAndDuesFragment;
import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.Models.Reminder;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class InventoryActivity extends AppCompatActivity {
    Button addInventoryItemBtn,refresh, increasequantity,deleteitem;
    RecyclerView inventoryListView;
    SearchView inventorySearchBar;
    FirebaseDatabase database;
    DatabaseReference inventoryReference, globalInventoryReference;
    UserDataService userDataService;
    InventoryManager inventoryManager;
    List<InventoryItem> suggestedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDataService = UserDataService.getInstance();
        database = FirebaseDatabase.getInstance();
        inventoryReference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Inventory");
        globalInventoryReference = database.getReference("GlobalInventory");
        inventoryManager = InventoryManager.getInstance();
        setContentView(R.layout.activity_inventory);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    @Override
    protected void onStart() {
        super.onStart();
        inventoryListView = findViewById(R.id.inventory_list);
        InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(InventoryActivity.this, inventoryManager.getInventoryItems());
        inventoryListView.setAdapter(inventoryListAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        inventoryListView.setLayoutManager(layoutManager);

        inventoryReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<InventoryItem> list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    InventoryItem invItem = dataSnapshot.getValue(InventoryItem.class);
                    list.add(invItem);
                }
                inventoryManager.setItems(list);
                inventoryListAdapter.setInventoryItemsList(list);
                inventoryListAdapter.notifyDataSetChanged();
//                InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(InventoryActivity.this, list);
//                inventoryListView.setAdapter(inventoryListAdapter);
//                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
//                inventoryListView.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(this, this, inventoryManager);
//
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
//        inventoryListView.setLayoutManager(layoutManager);
//        inventoryListView.setAdapter(inventoryListAdapter);

        this.addInventoryItemBtn = (Button) findViewById(R.id.additem);
        this.addInventoryItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this, AddInventoryItem.class);
                startActivity(intent);
            }
        });
        this.inventorySearchBar = (SearchView) findViewById(R.id.inventorySearchBar);
        this.inventorySearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<InventoryItem> filteredItems = new ArrayList<>();
                for (InventoryItem item:inventoryManager.getInventoryItems())
                {
                    if(item.getName().contains(query))
                    {
                        filteredItems.add(item);
                    }
                };
                inventoryListAdapter.setInventoryItemsList(filteredItems);
                inventoryListAdapter.notifyDataSetChanged();
//                InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(InventoryActivity.this, filteredItems);
//                inventoryListView.setAdapter(inventoryListAdapter);
//                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
//                inventoryListView.setLayoutManager(layoutManager);
//                inventoryListAdapter.setInventoryItemsList(filteredItems);
//                inventoryListAdapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<InventoryItem> filteredItems = new ArrayList<>();
                for (InventoryItem item : inventoryManager.getInventoryItems())
                {
                    if(item.getName().toLowerCase().contains(newText.toLowerCase()))
                    {
                        filteredItems.add(item);
                    }
                };
                inventoryListAdapter.setInventoryItemsList(filteredItems);
                inventoryListAdapter.notifyDataSetChanged();
//                InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(InventoryActivity.this, filteredItems);
//                inventoryListView.setAdapter(inventoryListAdapter);
//                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
//                inventoryListView.setLayoutManager(layoutManager);
//                inventoryListAdapter.setInventoryItemsList(filteredItems);
//                inventoryListAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void editItem(InventoryItem item)
    {
        Intent i = new Intent(this, AddInventoryItem.class);
        i.putExtra("editCard", item);
        startActivity(i);
    }
}

