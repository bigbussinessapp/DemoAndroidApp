package com.example.bigbusiness.Models;

public class User {

    private String uid , name , email_id , phoneNo ;
    private String organizationName, organizationType;
    private boolean isLoggedIn;

    public User(){

    }

    public User(String uid, String name, String email_id, String phoneNo, String organizationName, String organizationType) {
        this.uid = uid;
        this.name = name;
        this.email_id = email_id;
        this.phoneNo = phoneNo;
        this.organizationName = organizationName;
        this.organizationType = organizationType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }


}
