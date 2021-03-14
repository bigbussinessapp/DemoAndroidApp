package com.example.bigbusiness.Models;

import java.io.Serializable;

public class Reminder implements Serializable {

    //properties
    //title, amount, date, paymentType, deletebutton, editbutton


    String title , amount , paymentDate, paymentTime;
    String paymentType;
    String cardId;
    static int id = 0;

    public Reminder()
    {

    }
    public Reminder(String title, String amount, String paymentType, String paymentDate, String paymentTime)
    {
            this.title = title;
            this.amount = amount;
            this.paymentDate = paymentDate;
            this.paymentType = paymentType;
            this.paymentTime = paymentTime;
//            this.cardId = id++;

        //auto create
//        this.radioReceive = "Receive";
//        this.radioPay = "Pay";
        //createRadioButton(this.radioReceive);
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardId() {return cardId;}

    public void setCardId(String cardId)
    {
        this.cardId = cardId;
    }
}
