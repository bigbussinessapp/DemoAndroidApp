package com.example.bigbusiness.Main.ui.Invoice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bigbusiness.Models.GenerateInvoiceItem;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputEditText;

public class GenerateInvoice extends AppCompatDialogFragment {
    private TextInputEditText invoicename;
    private TextInputEditText buyername;
    private TextInputEditText name;
    private TextInputEditText quantity;
    private TextInputEditText price;
    Spinner unit_spinner;
    String getitemunits;
    String itemunts[]={"select quantity","0","1","2","3","4"};
    ArrayAdapter unitspinnerdapter;
    Button addItem;
    private GenerateInvoiceManager invoiceManager;
    RecyclerView invoiceListView;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.generate_invoicedialog, null);

        invoiceListView = view.findViewById(R.id.invoice_list);
        GenerateInvoiceListAdapter invoiceListAdapter = new GenerateInvoiceListAdapter(getActivity(),getActivity(), invoiceManager);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1 , StaggeredGridLayoutManager.VERTICAL);
        invoiceListView.setLayoutManager(layoutManager);
        invoiceListView.setAdapter(invoiceListAdapter);

        GenerateInvoiceDBHelper invoiceDBHelper = new GenerateInvoiceDBHelper(getActivity());
        invoiceManager = new GenerateInvoiceManager(invoiceDBHelper);
        name = view.findViewById(R.id.itemname);
        invoicename = view.findViewById(R.id.invoicename);

        price = view.findViewById(R.id.price);
        buyername = view.findViewById(R.id.buyername);
        quantity = view.findViewById(R.id.Quantity);
        unit_spinner = view.findViewById(R.id.unitspinner);
        unit_spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) getActivity());

        unitspinnerdapter = new ArrayAdapter(getActivity(), android.R.layout.select_dialog_item,itemunts);
        unit_spinner.setAdapter(unitspinnerdapter);

        addItem = view.findViewById(R.id.save);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item_name = name.getText().toString();
                String item_quantity = quantity.getText().toString();
                String inovice_name = invoicename.getText().toString();
                String buyer_name = buyername.getText().toString();
              //  int item_price = Integer.parseInt(price.getText().toString());
                String item_price = price.getText().toString();


                GenerateInvoiceItem newItem = new GenerateInvoiceItem(100,item_name, inovice_name, buyer_name,  item_price);

                    invoiceManager.addItem(newItem);
            }
        });


        builder.setView(view);
        builder.setTitle("Add Invoice")
        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }

}
