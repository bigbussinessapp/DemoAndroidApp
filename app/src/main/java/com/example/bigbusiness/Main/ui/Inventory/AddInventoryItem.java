package com.example.bigbusiness.Main.ui.Inventory;

import android.annotation.SuppressLint;
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
    String UOMList[]={"None","Milli Litre","Gram","Kilogram","Litre"};
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

    @SuppressLint("ResourceType")
    private void displayInventoryForm(InventoryItem cardToBeEdited) {

//        InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(this);
        inventoryManager = InventoryManager.getInstance();//new InventoryManager(inventoryDBHelper);
        name = findViewById(R.id.itemname);
        code = findViewById(R.id.itemcode);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.Quantity);
        units = findViewById(R.id.units);

        if(cardToBeEdited != null)
            code.getEditText().setFocusable(false);
//        additempicturebtn = findViewById(R.id.uploaditempicture);
        unit_spinner = findViewById(R.id.unitspinner);
        unitspinnerdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, UOMList);
        unit_spinner.setAdapter(unitspinnerdapter);
        unit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUOM = unit_spinner.getSelectedItem().toString();
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

            for(int i = 0; i < unit_spinner.getCount(); i++){
                if(unit_spinner.getItemAtPosition(i).toString().equals(cardToBeEdited.getUom())){
                    unit_spinner.setSelection(i);
                    break;
                }
            }
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

                if(item_code.isEmpty() || item_name.isEmpty() || item_price.isEmpty() ||
                    item_units.isEmpty() || item_quantity.isEmpty())
                {
                    Toast.makeText(AddInventoryItem.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }
                else if(item_code.contains("/"))
                {
                    Toast.makeText(AddInventoryItem.this, "Item Code Invalid", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    InventoryItem newItem = new InventoryItem(item_code, item_name, item_quantity, item_units, selectedUOM, item_price, null);//imageViewToByte(imageView));
                    if (cardToBeEdited != null) {
                        inventoryManager.updateItem(newItem);
                    } else {
                        inventoryManager.addItem(newItem);
                    }
                    Intent intent = new Intent(AddInventoryItem.this, InventoryActivity.class);
                    startActivity(intent);
                    finish();
                }
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