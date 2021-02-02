package com.example.BigBusiness;

public class Dues {
    String title;
    String price;
    String paymentType;
    int cardId;
    static int id = 0;

    public Dues()
    {

    }

    public Dues(String title, String price, String paymentType)
    {
        this.title = title;
        this.price = price;
        this.paymentType = paymentType;
        this.cardId = this.id++;
    }
}
