package com.example.bigbusiness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addinvoice extends AppCompatActivity {
    sqldbhelper mysqldbhelper;
    String base64data;
    Button addbtn;
    TextInputLayout invoicename,bizname,productname,productqty,productamt;
    TextView totalamt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinvoice);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        addbtn= (Button)findViewById(R.id.addid);
        isWriteStoragePermissionGranted();
        mysqldbhelper = new sqldbhelper(this);//calls constractor in sqldbhelper class




          addData();

          Button btn2 = (Button)findViewById(R.id.cancelid);
          Button  btnpdf = (Button)findViewById(R.id.invoicepick);
        invoicename = (TextInputLayout)findViewById(R.id.invoicenameip);
        bizname = (TextInputLayout)findViewById(R.id.biznameip);
        productname = (TextInputLayout)findViewById(R.id.nameip);
        productqty = (TextInputLayout)findViewById(R.id.qtyip);
        productamt = (TextInputLayout)findViewById(R.id.amountip);
        totalamt = (TextView) findViewById(R.id.totalamt);

        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAttachment();

            }
        });



          btn2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(addinvoice.this, (CharSequence) invoicename.getEditText().getText(), Toast.LENGTH_SHORT).show();
              }
          });


    }

    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted1");
                return true;
            } else {

                Log.v("tag","Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted1");
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted2");
                return true;
            } else {

                Log.v("TAG","Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted2");
            return true;
        }
    }





    private void addData() {
    addbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isWriteStoragePermissionGranted() == true ){
                if(invoicename.getEditText().getText().length()>0){
                    if(bizname.getEditText().getText().length()>0){
                        if(productname.getEditText().getText().length()>0){
                            if(productqty.getEditText().getText().length()>0){
                                if(productamt.getEditText().getText().length()>0){
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY_HH:mm:ss", Locale.getDefault());
                                    String currentDateandTime = sdf.format(new Date());

                                    String addtotal = String.valueOf(Integer.parseInt(productamt.getEditText().getText().toString())*Integer.parseInt(productqty.getEditText().getText().toString()));
                                    boolean inserted = mysqldbhelper.insertdata(invoicename.getEditText().getText().toString(),bizname.getEditText().getText().toString(),currentDateandTime,productname.getEditText().getText().toString(),Integer.parseInt(productqty.getEditText().getText().toString()),productamt.getEditText().getText().toString(),addtotal,base64data);
                                    if(inserted == true){
                                        Toast.makeText(addinvoice.this, "inserted data", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(addinvoice.this, "failed to insert data", Toast.LENGTH_SHORT).show();

                                    }



                                }else{

                                }

                            }else{

                            }

                        }else{

                        }

                    }else{

                    }

                }else{

                }


            }
        }
    });
    }

    public void goToAttachment(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent, "Select invoice pdf"), 200);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        switch (requestCode){
            case 200:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();

                    try {
                        InputStream is = getContentResolver().openInputStream(uri);
                        byte[] pdfbyte = new byte[is.available()];
                        is.read(pdfbyte);
                        base64data = Base64.encodeToString(pdfbyte,Base64.DEFAULT);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;
        }
    }


}