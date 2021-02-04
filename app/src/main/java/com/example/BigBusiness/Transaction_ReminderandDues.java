package com.example.BigBusiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Transaction_ReminderandDues extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_main);


//        TextInputEditText textField = (TextInputEditText) findViewById(R.id.textInput);
//        TextView textView = (TextView) findViewById(R.id.textView);
//        Button button = (Button) findViewById(R.id.button1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               String mytext = textField.getText().toString();
//                textView.setText(mytext);
//            }
//        });


    }

    @Override
    protected void onStart() {
        super.onStart();



        tabLayout = (TabLayout) findViewById(R.id.layTab_id);
        viewPager = (ViewPager) findViewById(R.id.viewPager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

//        Intent i = getIntent();
//        Tablayout.Tab t = tabLayout.getTabAt(2);
//        int tabPosition = (int)i.getIntExtra("tabIndex", tabLayout.getSelectedTabPosition());
//        tabLayout.selectTab(tabLayout.getTabAt(tabPosition));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        adapter.AddFragment(new TransactionFragment(), "Transaction History");
        adapter.AddFragment(new RemindersAndDuesFragment(), "Reminders and Dues");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }




}