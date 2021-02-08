package com.example.bigbusiness.Main.ui.Inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddInventoryItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "ItemDialog";
    private TextInputEditText name;
    private TextInputEditText quantity;
    private TextInputEditText units;
    private TextInputEditText price;
    Spinner unit_spinner;
    String getitemunits;
    String itemunts[]={"select unit","small","large","ML","L"};
    ArrayAdapter unitspinnerdapter;
    Button saveBtn, cancelBtn;
    String base64data;
    Button additempicturebtn;
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

        InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(this);
        inventoryManager = new InventoryManager(inventoryDBHelper);
        name = findViewById(R.id.itemname);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.Quantity);
        units = findViewById(R.id.units);

        additempicturebtn = findViewById(R.id.uploaditempicture);
        unit_spinner = findViewById(R.id.unitspinner);
        unit_spinner.setOnItemSelectedListener(AddInventoryItem.this);

        unitspinnerdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item,itemunts);
        unit_spinner.setAdapter(unitspinnerdapter);
//        mysqldhelperite = new mysqldbhelperitem(this);

        if(cardToBeEdited != null)
        {
            name.setText(cardToBeEdited.getName());
            price.setText(String.valueOf(cardToBeEdited.getPrice()));
            quantity.setText(String.valueOf(cardToBeEdited.getQuantity()));
            units.setText(cardToBeEdited.getUnit());
        }

        additempicturebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadItemImage();
            }

            public void uploadItemImage() {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("item/img");
                startActivityForResult(Intent.createChooser(intent, "Select item image"), 200);
            }
        });

        saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item_name = name.getText().toString();
                String item_units = getitemunits;
                int item_price = Integer.parseInt(price.getText().toString());
                String item_quantity = quantity.getText().toString();
                int item_quantity_num = Integer.parseInt(item_quantity);
                int item_price_num = item_price;

                InventoryItem newItem = new InventoryItem(100,item_name, item_quantity_num, item_units, item_price_num,  "invoice-"+item_name);
                if(cardToBeEdited != null)
                {
                    inventoryManager.editItem(newItem);
                }
                else
                {
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


//    public  boolean isWriteStoragePermissionGranted() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    == PackageManager.PERMISSION_GRANTED) {
//                Log.v("TAG","Permission is granted2");
//                return true;
//            } else {
//
//                Log.v("TAG","Permission is revoked2");
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//                return false;
//            }
//        }
//        else { //permission is automatically granted on sdk<23 upon installation
//            Log.v("TAG","Permission is granted2");
//            return true;
//        }
//    }

//    private void AddData(String name, String unit, String totpric, int pric, String quantit) {
//        boolean insertData = mysqldhelperite.addData(name,unit,totpric, String.valueOf(pric),quantit);
//        if(insertData){
//
//            Toast toast=Toast.makeText(AddInventoryItem.this,"Data Successfully Inserted",Toast.LENGTH_SHORT);
//            toast.show();
//        } else{
//            Toast toast=Toast.makeText(AddInventoryItem.this,"Something went wrong",Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        getitemunits = unit_spinner.getSelectedItem().toString();
        units.setText(getitemunits);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}