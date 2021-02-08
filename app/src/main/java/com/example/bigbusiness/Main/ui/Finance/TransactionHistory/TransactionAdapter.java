package com.example.bigbusiness.Main.ui.Finance.TransactionHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.TransactionHistoryItem;
import com.example.bigbusiness.R;

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
        TransactionHistoryItem t = transactionlist.get(position);
        holder.TransactionMerchant.setText(t.getTransactionMerchant());
        holder.TransactionAmount.setText(t.getTransactionAmount());
        if(t.getTransaction_type().equals("Received From"))
        {
            holder.TransactionAmount.setTextColor(ContextCompat.getColor(this.mContext, R.color.red));
        }
        else
        {
            holder.TransactionAmount.setTextColor(ContextCompat.getColor(this.mContext, R.color.green));
        }
        holder.date.setText(t.getDate());
        holder.TransactionType.setText(t.getTransaction_type());
        holder.checkInvoice.setOnClickListener(this.transactionFragment.checkInvoice);
    }

    @Override
    public int getItemCount() {
        return transactionlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //        private LinearLayout dialog_item;
        private TextView TransactionMerchant , invoice;
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
