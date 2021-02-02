package com.example.BigBusiness;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ReminderCardsManager  extends Fragment {
    ArrayList<Reminder> cards = new ArrayList<Reminder>();
    private static com.example.BigBusiness.ReminderCardsManager instance;

    public ReminderCardsManager()
    {
        cards.add(new Reminder("Demo1","1000","Receive", "10/09/2020","08:00 pm"));
        cards.add(new Reminder("Demo2","2000","Pay", "12/10/2030","09:00 pm"));
        cards.add(new Reminder("Demo3","3000","Receive", "11/09/2020","08:00 pm"));
        cards.add(new Reminder("Demo4","4000","Receive", "11/09/2020","08:00 pm"));
    }

    public void AddCard(Reminder card)
    {
        cards.add(card);
    }

    public ArrayList<Reminder> GetCards()
    {
        return cards;
    }

    public static com.example.BigBusiness.ReminderCardsManager getInstance()
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

    public Reminder getCardById(int id)
    {
        for(int i=0; i<cards.size(); i++)
        {
            if(cards.get(i).getCardId() == id)
            {
                return cards.get(i);
            }
        }
        return null;
    }

    public boolean replaceEditedCard(Reminder card)
    {
        int index = getIndex(card);
        removeCard(card);
        cards.add(index, card);
        return true;
    }

    public Reminder removeCard(Reminder card)
    {
        int index = getIndex(card);
        if(index == -1)
            return null;
        Reminder removedCard = cards.remove(index);
        return removedCard;
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
}
