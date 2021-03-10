package com.example.bigbusiness.Main.ui.Invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigbusiness.Main.ui.Inventory.InventoryManager;
import com.example.bigbusiness.Models.BuyerDetails;
import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.Models.InvoiceProduct;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddInvoiceActivity extends AppCompatActivity {

    private TextInputEditText invoiceName, buyerName, itemName, itemQuantity;
    TextView availableInventoryView;
    private Button addItemBtn, clearBtn, saveToPdfBtn;
    RecyclerView inventoryListView;
    InvoiceManager invoiceManager;
    String invoiceId;
    Spinner inventoryItemsSpinner;
    InventoryManager inventoryManager;
    int availableQuantity = 0;
    InventoryItem selectedItem = null;
    FirebaseDatabase database;
    DatabaseReference inventoryReference;
    UserDataService userDataService;
    private static int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);
        userDataService = UserDataService.getInstance();
        database = FirebaseDatabase.getInstance();
        inventoryReference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Inventory");

        invoiceManager = InvoiceManager.getInstance();
        inventoryListView = (RecyclerView) findViewById(R.id.inventory_list_view);

        invoiceName = findViewById(R.id.invoiceName);
        buyerName = findViewById(R.id.custName);
        addItemBtn = findViewById(R.id.addItemBtn);
        clearBtn = findViewById(R.id.clearBtn);
        saveToPdfBtn = findViewById(R.id.saveToPdfBtn);
        inventoryManager = InventoryManager.getInstance();
        inventoryItemsSpinner = findViewById(R.id.in_inv_item_spinner);
        availableInventoryView = findViewById(R.id.available_inv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //final InventoryItem[] selectedItem = new InventoryItem[1];
        invoiceId = "Invoice"+count++;

        List<String> inventoryItems = new ArrayList<>();
        inventoryManager.getInventoryItems().forEach(item -> inventoryItems.add(item.getName()));
        ArrayAdapter<String> invItemsAdapter = new ArrayAdapter<>(AddInvoiceActivity.this,
                android.R.layout.simple_list_item_1, inventoryItems );
        invItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inventoryItemsSpinner.setAdapter(invItemsAdapter);
        inventoryItemsSpinner.setOnItemSelectedListener(itemsSpinnerEvent(inventoryItems));
//
//        inventoryReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot item: snapshot.getChildren())
//                {
//                    InventoryItem invItem = item.getValue(InventoryItem.class);
//                    inventoryItems.add(invItem.getName());
//                }
//                invItemsAdapter.addAll(inventoryItems);
//                inventoryItemsSpinner.setOnItemSelectedListener(itemsSpinnerEvent(inventoryItems));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        inventoryManager.getInventoryItems().forEach(x -> {
//            inventoryItems.add(x.getName());
//        });

        List<InvoiceProduct> itemsList = invoiceManager.getAddedItems();
        InvoiceItemsAdapter invoiceItemsAdapter = new InvoiceItemsAdapter(this, itemsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        inventoryListView.setLayoutManager(layoutManager);
        inventoryListView.setAdapter(invoiceItemsAdapter);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQuantity = findViewById(R.id.quantity);

                String quantity = itemQuantity.getText().toString();
                if(selectedItem == null || quantity.isEmpty())
                {
                    Toast.makeText(AddInvoiceActivity.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(quantity) > availableQuantity)
                {
                    Toast.makeText(AddInvoiceActivity.this, "Only "+availableQuantity+" available!", Toast.LENGTH_SHORT).show();
                }
                else if(selectedItem == null)
                {
                    Toast.makeText(AddInvoiceActivity.this, "Item is null", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    InvoiceProduct item = new InvoiceProduct(String.valueOf(selectedItem.getItemCode()), quantity);
                    invoiceManager.addProduct(item);
                    availableQuantity -= Integer.parseInt(quantity);
                    availableInventoryView.setText("* AvailableInventory: "+String.valueOf(availableQuantity));
                    invoiceItemsAdapter.notifyDataSetChanged();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQuantity = findViewById(R.id.quantity);

                inventoryItemsSpinner.clearFocus();
                itemQuantity.setText("");
            }
        });

        saveToPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = invoiceName.getText().toString();
                BuyerDetails buyer = new BuyerDetails(buyerName.getText().toString());
                HashMap<String, InvoiceProduct> items = invoiceManager.getAddedItemsWithIds();

                if(name.isEmpty() || buyer.getName().isEmpty())
                {
                    Toast.makeText(AddInvoiceActivity.this, "Fill All details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    InvoiceItem invoiceItem = new InvoiceItem(name, buyer, items);
//                    invoiceItem.setInvoiceId(invoiceId);
                    invoiceManager.addInvoice(invoiceItem);
                    invoiceManager.clearItems();
                    Intent i = new Intent(AddInvoiceActivity.this, InvoiceManagementActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private AdapterView.OnItemSelectedListener itemsSpinnerEvent(List<String> inventoryItems)
    {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = inventoryManager.getInventoryItems().get(position);
                if(selectedItem == null)
                    Toast.makeText(AddInvoiceActivity.this, "Item not found in inventory", Toast.LENGTH_SHORT).show();
                availableQuantity = selectedItem.getQuantity() - invoiceManager.getAddedQuantityById(selectedItem.getItemCode());
                availableInventoryView.setText("* AvailableInventory: "+String.valueOf(availableQuantity));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
}