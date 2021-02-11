package com.example.bigbusiness.Main.ui.Invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Main.ui.Inventory.InventoryListAdapter;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.R;

import java.util.ArrayList;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.ViewHolder> {

        InvoiceManager inventoryManager;
        ArrayList<InvoiceItem> invoiceItemsList;
        InvoiceManagement invoice;
        Context context;

        public InvoiceListAdapter(Context context, InvoiceManagement invoice, InvoiceManager invoiceManager) {
            this.inventoryManager = invoiceManager;
        this.invoiceItemsList = this.inventoryManager.getInvoiceItems();
        this.invoice = invoice;
        this.context = context;
        }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InvoiceItem itemClicked = this.invoiceItemsList.get(position);
        holder.pdf_name.setText(itemClicked.getName());
    }


    @Override
    public int getItemCount() {
        return this.invoiceItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView pdf_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pdf_name = (TextView) itemView.findViewById(R.id.invoicepdf_name);
        }
    }
}
