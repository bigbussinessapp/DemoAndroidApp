package com.example.BigBusiness;

import java.io.Serializable;

public class Reminder implements Serializable {

    //properties
    //title, amount, date, paymentType, deletebutton, editbutton


    String title , amount , date , time ;
    String  btndel="DELETE" , btnedit = "EDIT";
    String paymentType;
    int cardId;
    static int id = 0;

    public Reminder(String title, String amount, String paymentType, String Date , String time)
    {
            this.title = title;
            this.amount = amount;
            this.date = Date;
            this.paymentType = paymentType;
            this.time = time;
            this.cardId = id++;
        
        //auto create
//        this.radioReceive = "Receive";
//        this.radioPay = "Pay";
        //createRadioButton(this.radioReceive);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date;
    }

    public String getBtndel() {
        return btndel;
    }

    public void setBtndel(String btndel) {
        this.btndel = btndel;
    }

    public String getBtnedit() {
        return btnedit;
    }

    public void setBtnedit(String btnedit) {
        this.btnedit = btnedit;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getCardId() {return cardId;}
}
