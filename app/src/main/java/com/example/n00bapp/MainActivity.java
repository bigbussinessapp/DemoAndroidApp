package com.example.n00bapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.layTab_id);
        viewPager = (ViewPager) findViewById(R.id.viewPager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //frag

        adapter.AddFragment(new FragTrans(),"Transactio History");
        adapter.AddFragment(new FragRemdrs(),"Reminders and Dues");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

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
}