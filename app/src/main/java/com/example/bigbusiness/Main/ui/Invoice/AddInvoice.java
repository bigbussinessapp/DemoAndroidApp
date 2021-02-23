package com.example.bigbusiness.Main.ui.Invoice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.bigbusiness.Models.AddInvoiceDataItem;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddInvoice extends AppCompatDialogFragment {
    private ArrayList<AddInvoiceDataItem> datalist;
    TextInputEditText nameinput;
    TextView pdfname;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_invoicedialog, null);
        nameinput = (TextInputEditText)view.findViewById(R.id.name);
        pdfname = (TextView)view.findViewById(R.id.pdfname);
        builder.setView(view);
        builder.setTitle("Add Invoice")
        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String getName = nameinput.getText().toString();
                String getPdfName = pdfname.getText().toString();
                datalist = new ArrayList<>();
                datalist.add(new AddInvoiceDataItem(getName,getPdfName));
                //datalist.add(new AddInvoiceDataItem(1,"vdfv.pfd",null));
                //datalist.add(new AddInvoiceDataItem(2,"fdcd.pfd",null));
                //datalist.add(new AddInvoiceDataItem(3,"dcds.pfd",null));
            }
        });
        return builder.create();
    }
}
