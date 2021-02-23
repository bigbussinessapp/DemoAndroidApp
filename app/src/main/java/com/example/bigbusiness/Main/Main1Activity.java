package com.example.bigbusiness.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.bigbusiness.Login.LoginActivity;
import com.example.bigbusiness.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class Main1Activity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    TextView NameoftheUser;
    Toolbar toolbar_title;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        toolbar_title = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_title);

//        NameoftheUser = findViewById(R.id.NameofTheUser);
//        firebaseDatabase.getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String userName = snapshot.child("name").getValue(String.class);
//                NameoftheUser.setText(userName);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        bottomNavigation.setOnNavigationItemSelectedListener(navSeletedItem);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,
//                new HomeFragment()).commit();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.homeFragment, R.id.addInvoiceFragment, R.id.profileFragment)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigation, navController);
//        NavigationUI.setupActionBarWithNavController(this, navController);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navSeletedItem =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment selectedFragment = null;
//
//                    switch(item.getItemId())
//                    {
//                        case R.id.navigation_home:
//                            selectedFragment = new HomeFragment();
//                            break;
//                        default:
//                            break;
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,
//                            selectedFragment).commit();
//                    return true;
//                }
//            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_btn:
                firebaseAuth.signOut();
                finish();
                Intent intent = new Intent(Main1Activity.this, LoginActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}