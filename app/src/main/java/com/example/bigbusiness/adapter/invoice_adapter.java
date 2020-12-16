package com.example.bigbusiness.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class invoice_adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Model> models;

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

        PDFView pf = (PDFView) view.findViewById(R.id.pdfview);


        sqldbhelper db = new sqldbhelper(context);
        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.getdata();

                    cursor.moveToPosition(position);
                    String f = cursor.getString(7);

                byte[] pdfAsBytes = Base64.decode(f, Base64.DEFAULT);

                Activity act = (Activity)context;

                act.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {

                        act.findViewById(R.id.pdflistscreen).setVisibility(View.GONE);
                        act.findViewById(R.id.pdflayout).setVisibility(View.VISIBLE);
                        if(act.findViewById(R.id.pdflayout).getVisibility() == View.VISIBLE){

                            pf.fromBytes(pdfAsBytes) .defaultPage(0) .enableAnnotationRendering(true) .scrollHandle(new DefaultScrollHandle(context)) .spacing(10).load();

                        }
                    } });



            }
        });

        Model model = models.get(position);

        invoicename.setText(model.getInvoiename());

        return convertView;
    }
}
