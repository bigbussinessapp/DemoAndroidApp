package com.example.bigbusiness.Main.ui.Inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.R;

import java.util.ArrayList;
import java.util.List;


public class InventoryActivity extends AppCompatActivity {
    Button addInventoryItemBtn,refresh, increasequantity,deleteitem;
    RecyclerView inventoryListView;
    SearchView inventorySearchBar;
    InventoryFBHelper InventoryFBHelper;
    List<InventoryItem> suggestedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
      //  InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(this);

        InventoryFBHelper = InventoryFBHelper.getInstance();
        InventoryManager inventoryManager = new InventoryManager(InventoryFBHelper);
        InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(this, this , inventoryManager);
//
       // InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(this, this, inventoryManager);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        inventoryListView.setLayoutManager(layoutManager);
        inventoryListView.setAdapter(inventoryListAdapter);

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
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<InventoryItem> filteredItems = new ArrayList<>();
                for (InventoryItem item:inventoryManager.getInventoryItems())
                {
                    if(item.getName().contains(newText))
                    {
                        filteredItems.add(item);
                    }
                };
                inventoryListAdapter.setInventoryItemsList(filteredItems);
                inventoryListAdapter.notifyDataSetChanged();
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

