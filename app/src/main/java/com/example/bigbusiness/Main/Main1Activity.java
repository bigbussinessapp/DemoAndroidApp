package com.example.bigbusiness.Main;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bigbusiness.Main.ui.home.HomeFragment;
import com.example.bigbusiness.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Main1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

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
}