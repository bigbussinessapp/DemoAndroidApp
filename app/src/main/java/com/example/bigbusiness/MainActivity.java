package com.example.bigbusiness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bigbusiness.adapter.invoice_adapter;
import com.example.bigbusiness.model.Model;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    sqldbhelper mysqldbhelper;
   private ListView listView;
   private ArrayList<Model> models;
   private invoice_adapter adapter;
   private  Button downloadbtn;

    LinearLayout pdfscreen;
    LinearLayout listscreen;
    ArrayList<Model> pdflist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysqldbhelper = new sqldbhelper(this);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);



        listView = (ListView)findViewById(R.id.pdflist);
        isReadStoragePermissionGranted();
        Cursor cursor = mysqldbhelper.getdata();

        if(cursor.getCount() == 0 ){
            Toast.makeText(this, "NO DATA FOUND", Toast.LENGTH_SHORT).show();

        }else{
            //update ui here

            while(cursor.moveToNext()){
                pdflist.add(new Model(cursor.getString(1), cursor.getString(7)));
            }

            models = pdflist;
            adapter = new invoice_adapter(MainActivity.this,models);
            listView.setAdapter(adapter);
        }





        Button btndemo = (Button)findViewById(R.id.demo);
        downloadbtn = (Button)findViewById(R.id.DLbtn);
        PDFView pdf = (PDFView) findViewById(R.id.pdfview);
        Button back = (Button)findViewById(R.id.backdl);
         listscreen = (LinearLayout)findViewById(R.id.pdflayout);
         pdfscreen = (LinearLayout)findViewById(R.id.pdflistscreen);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity act = MainActivity.this;
                act.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        Toast.makeText(act, "okokkok", Toast.LENGTH_SHORT).show();

                        listscreen.setVisibility(View.GONE);
                        pdfscreen.setVisibility(View.VISIBLE);
                    } });

            }
        });

        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btndemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,addinvoice.class));
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        switch (requestCode){

            case 1200:
                Toast.makeText(this, data.getData().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
    }
