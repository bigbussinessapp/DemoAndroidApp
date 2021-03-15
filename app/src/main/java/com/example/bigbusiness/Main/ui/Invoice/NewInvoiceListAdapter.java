package com.example.bigbusiness.Main.ui.Invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.R;

import java.util.List;

public class NewInvoiceListAdapter extends RecyclerView.Adapter<NewInvoiceListAdapter.ViewHolder> {
    List<InvoiceItem> invoicesList;
    Context context;

    public NewInvoiceListAdapter(Context context, List<InvoiceItem> invoices) {
        this.invoicesList = invoices;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_invoice_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InvoiceItem itemClicked = this.invoicesList.get(position);
        holder.invoice_name.setText(itemClicked.getInvoiceName());

        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Sharing "+itemClicked.getInvoiceName()+"...", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.invoicesList.size();
    }

    public void setInventoryItemsList(List<InvoiceItem> invoicesList) {
        this.invoicesList = invoicesList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView invoice_name;
        ImageView shareBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invoice_name = (TextView) itemView.findViewById(R.id.invoiceName);
            shareBtn = itemView.findViewById(R.id.invoice_share);
        }
    }
}
