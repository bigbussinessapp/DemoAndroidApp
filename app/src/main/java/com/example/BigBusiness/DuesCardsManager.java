package com.example.BigBusiness;

import java.util.ArrayList;

public class DuesCardsManager {
    ArrayList<Dues> duesCards = new ArrayList<Dues>();
    private static DuesCardsManager instance;

    public DuesCardsManager()
    {
        duesCards.add(new Dues("Demo1","1222","Receive"));
        duesCards.add(new Dues("Demo2","7453","Receive"));
        duesCards.add(new Dues("Demo3","486754","Pay"));
        duesCards.add(new Dues("Demo4","486789","Pay"));
    }

    public static DuesCardsManager getInstance()
    {
        if(instance == null)
        {
            instance = new DuesCardsManager();
        }
        return instance;
    }

    public ArrayList<Dues> getCards()
    {
        return duesCards;
    }

    public void addDues(Reminder card)
    {
        Dues duesCard = new Dues(card.getTitle(),card.getAmount(), card.getPaymentType());
        duesCards.add(duesCard);
    }

    public boolean delete(int id)
    {
        if(id >= duesCards.size())
            return false;
        duesCards.remove(id);
        return true;
    }
}
