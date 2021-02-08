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
import com.example.bigbusiness.Models.Reminder;
import com.example.bigbusiness.R;

import java.util.Random;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.viewHolder> {

    Context context;
    RemindersAndDuesFragment remindersAndDuesFragment;
    ReminderCardsManager rmManager;
    DuesCardsManager duesManager;
    DuesAdapter duesAdapter;

    public ReminderAdapter(Context context, RemindersAndDuesFragment remindersAndDuesFragment, ReminderCardsManager rmManager, DuesCardsManager duesManager, DuesAdapter duesAdapter) {
        this.context = context;
        this.remindersAndDuesFragment = remindersAndDuesFragment;
        this.rmManager = rmManager;
        this.duesManager = duesManager;
        this.duesAdapter = duesAdapter;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.reminder_card,parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Reminder cardClicked = this.rmManager.GetCards().get(position);
        int randomColor = holder.colors[new Random().nextInt(holder.colors.length)];
//        holder.viewColorTag.setBackgroundColor(randomColor);
        holder.title.setText(cardClicked.getTitle());
        holder.Amount.setText(cardClicked.getAmount());
        if(cardClicked.getPaymentType().equals("Receive"))
        {
            holder.Amount.setTextColor(Color.parseColor("#008000"));
        }
        else
        {
            holder.Amount.setTextColor(Color.RED);
        }
        holder.Date.setText(cardClicked.getDate());
        holder.time.setText(cardClicked.getTime());
        holder.buttonDelete.setOnClickListener(v -> {
            this.rmManager.removeCard(cardClicked);
            notifyDataSetChanged();
        });

        holder.buttonEdit.setOnClickListener(v -> {
//            list.remove(position);
            Reminder cardToBeEdited = cardClicked;//reminderCardsManager.createCard("title1","amount1" ,"Recieve" ," date" , "time");//data u want);
            remindersAndDuesFragment.editRemainderCard(cardToBeEdited);
            notifyDataSetChanged();
        });
//        holder.buttonEdit.setText(model.getBtnedit());

//        holder.buttonMoveToDues.setOnClickListener(v -> {
//            Reminder cardToBeMoved = this.rmManager.removeCard(cardClicked);
//            this.duesManager.addDues(cardToBeMoved);
//            notifyDataSetChanged();
//            duesAdapter.notifyDataSetChanged();
//        });
    }

    @Override
    public int getItemCount() {
        return this.rmManager.GetCards().size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView title , Amount , Date , time ;
        Button  buttonDelete , buttonEdit, buttonMoveToDues;
        View viewColorTag;
        int[] colors;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            Amount = itemView.findViewById(R.id.amount);
            Date = itemView.findViewById(R.id.Date);
//            viewColorTag = itemView.findViewById(R.id.viewColorTag);
            buttonDelete = itemView.findViewById(R.id.btndel);
            buttonEdit = itemView.findViewById(R.id.btnedit);
            colors = itemView.getResources().getIntArray(R.array.random_color);
            time = itemView.findViewById(R.id.TimeInput);
//            buttonMoveToDues = itemView.findViewById(R.id.moveToDues);
        }
    }


}