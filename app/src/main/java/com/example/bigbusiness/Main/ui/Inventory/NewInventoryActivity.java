package com.example.bigbusiness.Main.ui.Inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.R;

import java.util.ArrayList;
import java.util.List;

public class NewInventoryActivity extends AppCompatActivity {

    ImageView addInventoryImageView, noItemsImageView;
    RecyclerView inventoryListView;
    List<InventoryItem> items;
    InventoryManager inventoryManager;
    InventoryItem newItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_inventory_activity);
        addInventoryImageView = findViewById(R.id.add_inventory);
        inventoryListView = findViewById(R.id.inventory_card_list);
        noItemsImageView = findViewById(R.id.no_items_image);
        inventoryManager = InventoryManager.getInstance();
        items = new ArrayList<>();
        InventoryItem dummyItem = new InventoryItem("demo code", "demo name", "100", "250", "gms", "45", null);
        items.add(dummyItem);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent i = getIntent();
        newItem = (InventoryItem) i.getSerializableExtra("newItem");
        if(newItem != null)
        {
            items.add(newItem);
        }
        NewInventoryListAdapter inventoryListAdapter = new NewInventoryListAdapter(NewInventoryActivity.this, items);
        inventoryListView.setAdapter(inventoryListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        inventoryListView.setLayoutManager(layoutManager);

        if(items.size() > 0)
        {
            inventoryListView.setAlpha(1);
            noItemsImageView.setVisibility(View.INVISIBLE);
        }
        else
        {
            inventoryListView.setAlpha(0);
            noItemsImageView.setVisibility(View.VISIBLE);
        }
        addInventoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewInventoryActivity.this, NewAddInventoryActivity.class);
                startActivity(i);
            }
        });
    }
}