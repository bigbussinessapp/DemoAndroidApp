package com.example.bigbusiness.Main.ui.Inventory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddInventoryItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "ItemDialog";
    private TextInputEditText name;
    private TextInputEditText quantity;
    private TextInputEditText units;
    private TextInputEditText price;
    Spinner unit_spinner;
    String getitemunits;
    String itemunts[]={"select unit","Milli Litre","Gram","Kilogram","Litre","None"};
    ArrayAdapter unitspinnerdapter;
    Button saveBtn, cancelBtn;
    String base64data;
    Button additempicturebtn;
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageView;
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
        inventoryManager = InventoryManager.getInstance();//new InventoryManager(inventoryDBHelper);
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
            public void onClick(View v){
                ActivityCompat.requestPermissions(
                    AddInventoryItem.this,
                        new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE},
            REQUEST_CODE_GALLERY
                );
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

                InventoryItem newItem = new InventoryItem(100,item_name, item_quantity_num, item_units, item_price_num,  "invoice-"+item_name,null);//imageViewToByte(imageView));
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

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        getitemunits = unit_spinner.getSelectedItem().toString();
        units.setText(getitemunits);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}