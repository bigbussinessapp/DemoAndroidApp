package com.example.bigbusiness.Models;

public class Dues {
    String name;
    String price;
    String paymentType;
    String cardId;
//    static int id = 0;

    public Dues()
    {

    }

    public Dues(String name, String price, String paymentType)
    {
        this.name = name;
        this.price = price;
        this.paymentType = paymentType;
//        this.cardId = this.id++;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
