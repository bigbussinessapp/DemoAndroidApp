package com.example.bigbusiness.Services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bigbusiness.Login.RegistrationActivity;
import com.example.bigbusiness.Main.Main1Activity;
import com.example.bigbusiness.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDataService {
    private User loggedInUser;
    private boolean isLoggedIn;
    FirebaseDatabase database;
    DatabaseReference usersDatabase;
    FirebaseAuth mAuth;
    private static UserDataService instance;

    public static UserDataService getInstance()
    {
        if(instance == null)
        {
            instance = new UserDataService();
        }
        return instance;
    }

    private UserDataService()
    {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usersDatabase = database.getReference("Users");
    }

    public User getLoggedInUser()
    {
        return this.loggedInUser;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }
}
