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
    List<BusinessData> bData;
//    Dialog myDialog;

    public TransactionAdapter(Context mContext, List<BusinessData> bData) {
        this.mContext = mContext;
        this.bData = bData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(mContext).inflate(R.layout.transdataxml,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
//        vHolder.dialog_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext,"Test Clicked"+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
//            }
//        });
            return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bname.setText(bData.get(position).getBusinessName());
        holder.tamount.setText(bData.get(position).getTransAmount());

    }

    @Override
    public int getItemCount() {
        return bData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

//        private LinearLayout dialog_item;
        private TextView bname;
        private TextView tamount;

        public MyViewHolder(View itemView){
            super(itemView);
//            dialog_item = (LinearLayout) itemView.findViewById(R.id.dialog_box_id);
            bname = (TextView) itemView.findViewById(R.id.businessname_id);
            tamount =(TextView) itemView.findViewById(R.id.transamount_id);
        }

    }

}
