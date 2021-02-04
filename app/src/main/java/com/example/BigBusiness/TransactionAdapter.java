package com.example.BigBusiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    Context mContext;
    List<TransactionModel> transactionlist;

    public TransactionAdapter(Context mContext, List<TransactionModel> transactionlist ) {
        this.mContext = mContext;
        this.transactionlist = transactionlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(mContext).inflate(R.layout.transdataxml,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
            return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TransactionMerchant.setText(transactionlist.get(position).getTransactionMerchant());
        holder.TransactionAmount.setText(transactionlist.get(position).getTransactionAmount());
        holder.date.setText(transactionlist.get(position).getDate());
        holder.TransactionType.setText(transactionlist.get(position).getTransaction_type());
        holder.paymentType.setText(transactionlist.get(position).getTransaction_credited_debited());
    }

    @Override
    public int getItemCount() {
        return transactionlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

//        private LinearLayout dialog_item;
        private TextView TransactionMerchant , invoice , paymentType;
        private TextView TransactionAmount , TransactionType , date;

        public MyViewHolder(View itemView){
            super(itemView);
            TransactionMerchant = (TextView) itemView.findViewById(R.id.txv_transaction_merchant);
            TransactionAmount =(TextView) itemView.findViewById(R.id.txv_transactions_amount);
            date = (TextView) itemView.findViewById(R.id.txv_transaction_date);
            TransactionType = itemView.findViewById(R.id.txv_transactions_type);
            paymentType = (TextView) itemView.findViewById(R.id.txv_transaction_credited_debited);
        }

    }

}
