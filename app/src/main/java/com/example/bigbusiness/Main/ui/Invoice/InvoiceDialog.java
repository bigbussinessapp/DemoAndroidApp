package com.example.bigbusiness.Main.ui.Invoice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.bigbusiness.R;

public class InvoiceDialog extends AppCompatDialogFragment {

    Button addFromDeviceButton, generateInvoice;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.invoicedialog, null);
        builder.setView(view)
        .setPositiveButton("ADD FROM DEVICE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                openAddDialog();
            }

            private void openAddDialog() {
                AddInvoice addInvoicedialog = new AddInvoice();
                addInvoicedialog.show(getParentFragmentManager(),"Add invoice dialog");
            }
        })

                .setNegativeButton("GENERATE INVOICE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openGenrateDialog();
                    }

                    private void openGenrateDialog() {
                        GenerateInvoice genrateInvoicedialog = new GenerateInvoice();
                        genrateInvoicedialog.show(getParentFragmentManager(),"genrate invoice dialog");
                    }
                });
        return builder.create();

    }

}
