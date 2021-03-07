package com.example.bigbusiness.Main.ui.Invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.R;

import java.util.List;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.ViewHolder> {

        InvoiceManager inventoryManager;
        List<InvoiceItem> invoicesList;
        InvoiceManagementActivity invoice;
        Context context;

        public InvoiceListAdapter(Context context, InvoiceManagementActivity invoice, InvoiceManager invoiceManager) {
            this.inventoryManager = invoiceManager;
            this.invoicesList = this.inventoryManager.getAllInvoices();
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
        InvoiceItem itemClicked = this.invoicesList.get(position);
//        holder.pdf_name.setText(itemClicked.getName());
    }


    @Override
    public int getItemCount() {
        return this.invoicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView pdf_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pdf_name = (TextView) itemView.findViewById(R.id.invoicepdf_name);
        }
    }
}
