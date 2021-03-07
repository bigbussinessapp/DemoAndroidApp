package com.example.bigbusiness.Main.ui.Finance.RemindersAndDues;

import com.example.bigbusiness.Models.Dues;
import com.example.bigbusiness.Services.UserDataService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DuesFBHelper {
    private static DuesFBHelper instance;
    ArrayList<Dues> cards = new ArrayList<Dues>();
    FirebaseDatabase database;
    DatabaseReference reference;
    UserDataService userDataService;

    public static DuesFBHelper getInstance()
    {
        if(instance == null)
        {
            instance = new DuesFBHelper();
        }
        return instance;
    }

    private DuesFBHelper()
    {
        userDataService = UserDataService.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users")
                .child(userDataService.getLoggedInUser().getUid()).child("Dues");
    }

    public void addCard(Dues duesCard)
    {
        String key = reference.push().getKey();
        duesCard.setCardId(key);
        reference.child(key).setValue(duesCard);
    }

    public void removeCard(Dues card)
    {
        reference.child(card.getCardId()+"").removeValue();
    }
}
