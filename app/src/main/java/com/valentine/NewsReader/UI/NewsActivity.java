package com.valentine.NewsReader.UI;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.valentine.NewsReader.R;
import com.valentine.NewsReader.Showstories;
import com.valentine.NewsReader.TopstoriesFragment;

public class NewsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.Fragment selectedFragment =null;
            switch (item.getItemId()) {
                case R.id.navigation_Top:
                    selectedFragment = TopstoriesFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                    break;
                case R.id.navigation_Job:
                    Intent in =new Intent(getBaseContext(),MainActivity.class);
                    startActivity(in);
                     break;
                case R.id.navigation_Show:
                     in =new Intent(getBaseContext(),Showstories.class);
                    startActivity(in);
                    break;
            }
            return false;
        }
    };



}
