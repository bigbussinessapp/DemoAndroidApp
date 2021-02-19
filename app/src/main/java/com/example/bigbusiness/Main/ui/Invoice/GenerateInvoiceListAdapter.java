package com.example.bigbusiness.Main.ui.Invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.GenerateInvoiceItem;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class GenerateInvoiceListAdapter extends RecyclerView.Adapter<GenerateInvoiceListAdapter.Viewholder> {
    GenerateInvoiceManager invoiceManager;
    ArrayList<GenerateInvoiceItem> invoiceItemsList ;
    FragmentActivity invoice;
    Context context;

    public GenerateInvoiceListAdapter(Context context, FragmentActivity invoice, GenerateInvoiceManager invoiceManager) {
        this.invoiceManager = invoiceManager;
        this.invoiceItemsList = this.invoiceManager.getInvoiceItems();
        this.invoice = invoice;
        this.context = context;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generate_invoicedialog_listview, parent, false);
        return new GenerateInvoiceListAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        GenerateInvoiceItem itemClicked = this.invoiceItemsList.get(position);
        holder.invoicename.setText(itemClicked.getInvoicename());
        holder.name.setText(itemClicked.getName());
        holder.buyername.setText(itemClicked.getBuyername());
        holder.price.setText(itemClicked.getPrice());
    }

    @Override
    public int getItemCount() {
        return this.invoiceItemsList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextInputEditText invoicename;
        TextInputEditText buyername;
        TextInputEditText name;
        //TextInputEditText quantity;
        TextInputEditText price;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            invoicename = (TextInputEditText)itemView.findViewById(R.id.invoicename);
            buyername = (TextInputEditText)itemView.findViewById(R.id.buyername);
            name = (TextInputEditText)itemView.findViewById(R.id.name);
            price = (TextInputEditText)itemView.findViewById(R.id.price);

        }

    }
}
