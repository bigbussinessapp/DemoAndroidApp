package com.example.bigbusiness.Main.ui.Finance.RemindersAndDues;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bigbusiness.Models.Reminder;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReminderFBHelper {

    private static ReminderFBHelper instance;
    ArrayList<Reminder> cards = new ArrayList<Reminder>();
    FirebaseDatabase database;
    DatabaseReference reference;
    UserDataService userDataService;

    public static ReminderFBHelper getInstance()
    {
        if(instance == null)
        {
            instance = new ReminderFBHelper();
        }
        return instance;
    }

    private ReminderFBHelper()
    {
        userDataService = UserDataService.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Reminders");
    }

    public void addCard(Reminder reminderCard)
    {
        String key = reference.push().getKey();
        reminderCard.setCardId(key);
        reference.child(key).setValue(reminderCard);
    }

    public void removeCard(Reminder card)
    {
        reference.child(card.getCardId()+"").removeValue();
    }

    public void updateCard(Reminder card)
    {
        reference.child(card.getCardId()).setValue(card);
    }

    public ArrayList<Reminder> getAllCards()
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object o = snapshot.getChildren();
                for (DataSnapshot item : snapshot.getChildren())
                {
                    Reminder c = item.getValue(Reminder.class);
                    cards.add(c);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                cards.add(snapshot.getValue(Reminder.class));
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        int count = cards.size();
        return cards;
    }

}
