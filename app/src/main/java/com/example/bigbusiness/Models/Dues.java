package com.example.bigbusiness.Models;

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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String titlr) {
        this.title = title;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
