package com.example.bigbusiness.Main.ui.Invoice;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.InvoiceProduct;
import com.example.bigbusiness.R;

import java.util.List;

public class InvoiceItemsAdapter extends RecyclerView.Adapter<InvoiceItemsAdapter.viewHolder>  {

    private List<InvoiceProduct> items;
    private Context context;
    InvoiceManager invoiceManager;
    private Button addItemsBtn;

    public InvoiceItemsAdapter(Context context, List<InvoiceProduct> items)
    {
        this.context = context;
        this.items = items;
        this.invoiceManager = InvoiceManager.getInstance();
    }

    @NonNull
    @Override
    public InvoiceItemsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.generate_invoicedialog_listview,parent, false);
        return new InvoiceItemsAdapter.viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InvoiceItemsAdapter.viewHolder holder, int position) {
        InvoiceProduct item = this.invoiceManager.getAddedItems().get(position);
        holder.name.setText(item.getName(item.getProductCode()));
        holder.quantity.setText(String.valueOf(item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return this.invoiceManager.getAddedItems().size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name , quantity;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.in_inv_item_name);
            quantity = itemView.findViewById(R.id.in_inv_item_quantity);
        }
    }
}
