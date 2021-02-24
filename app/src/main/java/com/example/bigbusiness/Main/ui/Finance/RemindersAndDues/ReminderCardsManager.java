package com.example.bigbusiness.Main.ui.Finance.RemindersAndDues;

import androidx.fragment.app.Fragment;

import com.example.bigbusiness.Models.Reminder;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReminderCardsManager extends Fragment {
    public ArrayList<Reminder> cards = new ArrayList<Reminder>();
    private static ReminderCardsManager instance;
    public ReminderFBHelper reminderFBHelper;
    FirebaseDatabase database;
    DatabaseReference reference;
    UserDataService userDataService;

    private ReminderCardsManager()
    {
        reminderFBHelper = ReminderFBHelper.getInstance();
//        userDataService = UserDataService.getInstance();
//        database = FirebaseDatabase.getInstance();
//        reference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Reminders");
//        GetCards();
        cards.add(new Reminder("Demo1","1000","Receive", "10/09/2020","08:00 pm"));
        cards.add(new Reminder("Demo2","2000","Pay", "12/10/2030","09:00 pm"));
        cards.add(new Reminder("Demo3","3000","Receive", "11/09/2020","08:00 pm"));
        cards.add(new Reminder("Demo4","4000","Receive", "11/09/2020","08:00 pm"));
    }

    public void addCard(Reminder card)
    {
        reminderFBHelper.addCard(card);
//        cards.add(card);
    }

    public ArrayList<Reminder> GetCards()
    {
//        cards = reminderFBHelper.getAllCards();
        return cards;
    }

    public static ReminderCardsManager getInstance()
    {
        if(instance == null)
        {
            instance = new ReminderCardsManager();
        }
        return instance;
    }

    //Implement this
    public Reminder createCard(String title, String amount, String paymentType, String date , String time)
    {
        Reminder newCard = new Reminder(title,amount , paymentType , date , time);
        return newCard;
    }

//    public Reminder getCardById(int id)
//    {
//        for(int i=0; i<cards.size(); i++)
//        {
//            if(cards.get(i).getCardId() == id)
//            {
//                return cards.get(i);
//            }
//        }
//        return null;
//    }

    public void updateCard(Reminder card)
    {
        reminderFBHelper.updateCard(card);
//        int index = getIndex(card);
//        removeCard(card);
//        cards.add(index, card);
//        return true;
    }

    public void removeCard(Reminder card)
    {
        this.reminderFBHelper.removeCard(card);
//        int index = getIndex(card);
//        if(index == -1)
//            return null;
//        Reminder removedCard = cards.remove(index);
//        return removedCard;
    }

    public int getIndex(Reminder card)
    {
        int index = -1;
        for(int i=0; i<cards.size(); i++)
        {
            if(cards.get(i).getCardId() == card.getCardId())
            {
                index = i;
                break;
            }
        }
        return index;
    }

    public ArrayList<Reminder> getCards() {
        return cards;
    }
}
