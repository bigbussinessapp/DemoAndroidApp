package com.example.bigbusiness.Main.ui.Inventory;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddInventoryItem extends AppCompatActivity {
    private TextInputLayout name, code, quantity, units, price;
    Spinner unit_spinner;
    String selectedUOM;
    String UOMList[]={"select unit","Milli Litre","Gram","Kilogram","Litre","None"};
    ArrayAdapter unitspinnerdapter;
    Button saveBtn, cancelBtn;
//    String base64data;
//    Button additempicturebtn;
    final int REQUEST_CODE_GALLERY = 999;
//    ImageView imageView;
    private InventoryManager inventoryManager;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent i = getIntent();
        InventoryItem card = (InventoryItem)i.getSerializableExtra("editCard");
        displayInventoryForm(card);
    }

    private void displayInventoryForm(InventoryItem cardToBeEdited) {

//        InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(this);
        inventoryManager = InventoryManager.getInstance();//new InventoryManager(inventoryDBHelper);
        name = findViewById(R.id.itemname);
        code = findViewById(R.id.itemcode);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.Quantity);
        units = findViewById(R.id.units);

//        additempicturebtn = findViewById(R.id.uploaditempicture);
        unit_spinner = findViewById(R.id.unitspinner);
        unitspinnerdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, UOMList);
        unit_spinner.setAdapter(unitspinnerdapter);

        unit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUOM = UOMList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (cardToBeEdited != null) {
            name.getEditText().setText(cardToBeEdited.getName());
            code.getEditText().setText(cardToBeEdited.getItemCode());
            price.getEditText().setText(String.valueOf(cardToBeEdited.getPrice()));
            quantity.getEditText().setText(String.valueOf(cardToBeEdited.getQuantity()));
            units.getEditText().setText(cardToBeEdited.getUnit());
        }

//        additempicturebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                ActivityCompat.requestPermissions(
//                    AddInventoryItem.this,
//                        new String[]{
//                Manifest.permission.READ_EXTERNAL_STORAGE},
//            REQUEST_CODE_GALLERY
//                );
//            }
//
//
//        });

        saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item_code = code.getEditText().getText().toString();
                String item_name = name.getEditText().getText().toString();
                String item_units = units.getEditText().getText().toString();
                String item_price = price.getEditText().getText().toString();
                String item_quantity = quantity.getEditText().getText().toString();

                InventoryItem newItem = new InventoryItem(item_code, item_name, item_quantity, item_units, selectedUOM, item_price, "invoice-" + item_name, null);//imageViewToByte(imageView));
                if (cardToBeEdited != null) {
                    inventoryManager.updateItem(newItem);
                } else {
                    inventoryManager.addItem(newItem);
                }
                Intent intent = new Intent(AddInventoryItem.this, InventoryActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn = findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddInventoryItem.this, InventoryActivity.class);
                startActivity(intent);
            }
        });
    }
}