package com.example.bigbusiness.Main.ui.Inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputEditText;

public class NewAddInventoryActivity extends AppCompatActivity {

    TextInputEditText nameET, codeEt, quantityET, unitsET, uomET, priceET;
    String name, code, quantity, units, uom, price;
    Button cancelBtn, saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_add_inventory);
        saveBtn = findViewById(R.id.save_add_inventory);
        cancelBtn = findViewById(R.id.cancel_add_inventory);
        nameET = findViewById(R.id.name_inventory);
        codeEt = findViewById(R.id.code_inventory);
        quantityET = findViewById(R.id.quantity_inventory);
        unitsET = findViewById(R.id.units_inventory);
        uomET = findViewById(R.id.uom_inventory);
        priceET = findViewById(R.id.price_inventory);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent i = getIntent();
        InventoryItem item = (InventoryItem) i.getSerializableExtra("editCard");
        if(item != null)
        {
            nameET.setText(item.getName());
            quantityET.setText(item.getQuantity()+"");
            codeEt.setText(item.getItemCode());
            unitsET.setText(item.getUnit());
            uomET.setText(item.getUom());
            priceET.setText(item.getPrice()+"");
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameET.getText().toString();
                code = codeEt.getText().toString();
                quantity = quantityET.getText().toString();
                units = unitsET.getText().toString();
                uom = uomET.getText().toString();
                price = priceET.getText().toString();
                if(name.isEmpty() || code.isEmpty() || quantity.isEmpty() || units.isEmpty() || uom.isEmpty() || price.isEmpty())
                {
                    Toast.makeText(NewAddInventoryActivity.this, "Fill all details !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                InventoryItem item = new InventoryItem(name, code, quantity, units, uom, price, null);
                Intent i = new Intent(NewAddInventoryActivity.this, NewInventoryActivity.class);
                i.putExtra("newItem", item);
                startActivity(i);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewAddInventoryActivity.this, NewInventoryActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}