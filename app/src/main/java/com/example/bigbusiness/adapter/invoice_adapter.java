package com.example.bigbusiness.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUtils;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigbusiness.MainActivity;
import com.example.bigbusiness.R;
import com.example.bigbusiness.model.Model;
import com.example.bigbusiness.sqldbhelper;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.shockwave.pdfium.PdfDocument;


import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class invoice_adapter extends BaseAdapter{
    PDFView pf;
    private Context context;
    private ArrayList<Model> models;
    byte[] pdfAsBytes;
    Cursor cursor;
    public invoice_adapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = View.inflate(context, R.layout.invoiceviewdesign,null);

        }

        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.activity_main,null);

        TextView invoicename = convertView.findViewById(R.id.userenteredname);
        Button downloadbtn = convertView.findViewById(R.id.downloadbtn);
        Button invoicebtn = convertView.findViewById(R.id.sharebtn);

         pf = (PDFView) ((Activity)context).findViewById(R.id.pdfview);


        sqldbhelper db = new sqldbhelper(context);
        Activity act = (Activity)context;
        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = db.getdata();

                    cursor.moveToPosition(position);
                    String f = cursor.getString(8);


                act.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {

                        cursor = db.getdata();

                        cursor.moveToPosition(position);
                        String f = cursor.getString(8);

                        pdfAsBytes = Base64.decode(f, Base64.DEFAULT);

                        pf.fromBytes(pdfAsBytes).load();

                        act.findViewById(R.id.pdflistscreen).setVisibility(View.GONE);
                        act.findViewById(R.id.pdflayout).setVisibility(View.VISIBLE);

                    } });



            }
        });

        invoicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharepdfdata(db,position,act);

            }
        });


        Button viewdownloadbtn = act.findViewById(R.id.DLbtn);

        viewdownloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String f = cursor.getString(8);

                byte[] pdfAsBytes = Base64.decode(f, Base64.DEFAULT);
                Path path = Paths.get(Environment.getExternalStorageDirectory().toString()+"/"+cursor.getString(1)+".pdf");
                try {
                    Path files = Files.write(path, pdfAsBytes);
                    Toast.makeText(act, "Saved pdf to "+Environment.getExternalStorageDirectory().toString()+"/"+cursor.getString(1)+".pdf", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        Model model = models.get(position);

        invoicename.setText(model.getInvoiename());

        return convertView;
    }

    private void sharepdfdata(sqldbhelper db, int position, Activity act) {

        cursor = db.getdata();
        cursor.moveToPosition(position);
        String f = cursor.getString(8);

        byte[] pdfAsBytes = Base64.decode(f, Base64.DEFAULT);
        Path path = Paths.get(Environment.getExternalStorageDirectory().toString()+"/"+cursor.getString(1)+".pdf");

        try {
            Path files = Files.write(path, pdfAsBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        File fileWithinMyDir = new File(Environment.getExternalStorageDirectory().toString()+"/"+cursor.getString(1)+".pdf");

        if(fileWithinMyDir.exists()) {
            intentShareFile.setType("application/pdf");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().toString()+"/"+cursor.getString(1)+".pdf"));

            intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                    "Sharing File...");
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");

            act.startActivityForResult(Intent.createChooser(intentShareFile, "Share File"),1200);


        }


    }


}
