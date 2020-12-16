package com.example.bigbusiness.model;

public class Model {

    private String invoiename;

    public Model(String invoiename, String pdf) {
        this.invoiename = invoiename;

    }

    public String getInvoiename() {
        return invoiename;
    }

    public void setInvoiename(String invoiename) {
        this.invoiename = invoiename;
    }


}
