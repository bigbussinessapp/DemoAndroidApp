package com.example.bigbusiness.Main.ui.Finance.RemindersAndDues;

import com.example.bigbusiness.Models.Dues;
import com.example.bigbusiness.Models.Reminder;

import java.util.ArrayList;

public class DuesCardsManager {
    ArrayList<Dues> duesCards = new ArrayList<Dues>();
    private static DuesCardsManager instance;
    private DuesFBHelper duesFBHelper;

    public DuesCardsManager()
    {
        duesFBHelper = DuesFBHelper.getInstance();
        addDues(new Dues("Demo1","1222","Receive"));
        addDues(new Dues("Demo3","486754","Pay"));
        addDues(new Dues("Demo4","486789","Pay"));
//        duesCards.add(new Dues("Demo1","1222","Receive"));
//        duesCards.add(new Dues("Demo2","7453","Receive"));
//        duesCards.add(new Dues("Demo3","486754","Pay"));
//        duesCards.add(new Dues("Demo4","486789","Pay"));
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

    public void addDues(Dues card)
    {
        this.duesFBHelper.addCard(card);
//        Dues duesCard = new Dues(card.getTitle(),card.getPrice(), card.getPaymentType());
//        duesCards.add(duesCard);
    }

    public void delete(Dues card)
    {
        this.duesFBHelper.removeCard(card);
//        if(id >= duesCards.size())
//            return false;
//        duesCards.remove(id);
//        return true;
    }
}
