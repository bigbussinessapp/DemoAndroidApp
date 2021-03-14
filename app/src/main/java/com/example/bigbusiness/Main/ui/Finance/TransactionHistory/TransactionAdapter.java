package com.example.bigbusiness.Main.ui.Finance.TransactionHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Main.ui.Invoice.InvoiceManager;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.Models.TransactionHistoryItem;
import com.example.bigbusiness.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    Context mContext;
    List<TransactionHistoryItem> transactionlist;
    TransactionHistoryFragment transactionFragment;

    public TransactionAdapter(Context mContext, List<TransactionHistoryItem> transactionlist, TransactionHistoryFragment transactionFragment) {
        this.mContext = mContext;
        this.transactionlist = transactionlist;
        this.transactionFragment = transactionFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(mContext).inflate(R.layout.transaction_history_card,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TransactionHistoryItem itemClicked = transactionlist.get(position);
        holder.TransactionMerchant.setText(itemClicked.getTransactionMerchant());
        holder.TransactionAmount.setText(itemClicked.getTransactionAmount());
        if(itemClicked.getTransaction_type().equals("Received From"))
        {
            holder.TransactionAmount.setTextColor(ContextCompat.getColor(this.mContext, R.color.green));
        }
        else
        {
            holder.TransactionAmount.setTextColor(ContextCompat.getColor(this.mContext, R.color.red));
        }

        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy, HH: mm");
        try {
            Date formattedDate = format.parse(itemClicked.getDate());
            long differenceInDays = transactionFragment.getDifferenceInDays(formattedDate);
            if(differenceInDays > 0 && differenceInDays < 7)
            {
                holder.date.setText(differenceInDays+ " days ago");
            }
            else if(differenceInDays == 0)
            {
                holder.date.setText("Today");
            }
            else
            {
                holder.date.setText(itemClicked.getDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            holder.date.setText(itemClicked.getDate());
        }
        holder.TransactionType.setText(itemClicked.getTransaction_type());
        holder.checkInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvoiceItem invoice = InvoiceManager.getInstance().getInvoiceById(itemClicked.getInvoiceId());
                if(invoice != null)
                {
                    Toast.makeText(mContext, "Found invoice with name - "+invoice.getInvoiceName(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(mContext, "No invoices found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView TransactionMerchant;
        Button checkInvoice;
        private TextView TransactionAmount , TransactionType , date;

        public MyViewHolder(View itemView){
            super(itemView);
            TransactionMerchant = (TextView) itemView.findViewById(R.id.txv_transaction_merchant);
            TransactionAmount =(TextView) itemView.findViewById(R.id.txv_transactions_amount);
            date = (TextView) itemView.findViewById(R.id.txv_transaction_date);
            TransactionType = itemView.findViewById(R.id.txv_transactions_type);
            checkInvoice = (Button) itemView.findViewById(R.id.checkInvoice);
        }

    }

}
