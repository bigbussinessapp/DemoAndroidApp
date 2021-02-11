package com.example.bigbusiness.Models;

import java.io.Serializable;

public class InvoiceItem implements Serializable {
    static int id = 0;
    String name;


    public InvoiceItem(int id, String name) {
        this.name = name;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        InvoiceItem.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}