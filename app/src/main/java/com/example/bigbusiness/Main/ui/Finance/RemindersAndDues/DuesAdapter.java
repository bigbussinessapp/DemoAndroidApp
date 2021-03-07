package com.example.bigbusiness.Main.ui.Finance.RemindersAndDues;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.Dues;
import com.example.bigbusiness.R;

import java.util.List;
import java.util.Random;

public class DuesAdapter extends RecyclerView.Adapter<DuesAdapter.viewHolder> {
    Context context;
    RemindersAndDuesFragment remindersAndDuesFragment;
    DuesCardsManager duesCardsManager;
    List<Dues> duesList;

    public DuesAdapter(Context context, List<Dues> duesList, RemindersAndDuesFragment remindersAndDuesFragment)
    {
        this.context = context;
        this.remindersAndDuesFragment = remindersAndDuesFragment;
        this.duesList = duesList;
        this.duesCardsManager = DuesCardsManager.getInstance();
    }
    public DuesAdapter(Context context, RemindersAndDuesFragment remindersAndDuesFragment, DuesCardsManager duesCardsManager) {
        this.context = context;
        this.remindersAndDuesFragment = remindersAndDuesFragment;
        this.duesCardsManager = duesCardsManager;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.dues_card ,parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Dues cardClicked = this.duesList.get(position);
//        int randomColor = holder.colors[new Random().nextInt(holder.colors.length)];
//        holder.viewColorTag.setBackgroundColor(randomColor);
        holder.title.setText(cardClicked.getName());
        holder.price.setText(cardClicked.getPrice());
        if(cardClicked.getPaymentType().equals("Receive"))
        {
            holder.price.setTextColor(Color.parseColor("#008000"));
        }
        else
        {
            holder.price.setTextColor(Color.RED);
        }
        holder.buttonDelete.setOnClickListener(v -> {
            this.duesCardsManager.delete(cardClicked);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return duesList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView title , price;
        Button buttonDelete ;
        View viewColorTag;
        int[] colors;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
//            viewColorTag = itemView.findViewById(R.id.viewColorTag);
            buttonDelete = itemView.findViewById(R.id.btndel);
            colors = itemView.getResources().getIntArray(R.array.random_color);
        }
    }
}
