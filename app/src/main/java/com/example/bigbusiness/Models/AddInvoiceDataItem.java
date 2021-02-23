package com.example.bigbusiness.Models;

public class AddInvoiceDataItem {
    String pdfname;
    int pdf_ID;
    byte[] pdf;
    String pdfnameinput;
    public AddInvoiceDataItem(String pdfnameinput, String pdfname){
        //this.pdf_ID = id;
        this.pdfname = pdfname;
        this.pdfnameinput = pdfname;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public int getPdf_ID() {
        return pdf_ID;
    }

    public void setPdf_ID(int pdf_ID) {
        this.pdf_ID = pdf_ID;
    }

    public String getPdfname() {
        return pdfname;
    }

    public void setPdfname(String pdfname) {
        this.pdfname = pdfname;
    }
}
