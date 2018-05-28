package com.valentine.NewsReader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.valentine.NewsReader.UI.ASkFragment;
import com.valentine.NewsReader.UI.JobFragment;
import com.valentine.NewsReader.UI.ShowstoriesFragment;
import com.valentine.NewsReader.UI.TopStoriesFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedfragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_Top:
                    selectedfragment = TopStoriesFragment.newInstance();
                    break;
                case R.id.navigation_Job:
                    selectedfragment = JobFragment.newInstance();
                    break;
                case R.id.navigation_Show:
                    selectedfragment = ShowstoriesFragment.newInstance();
                    break;
                case R.id.navigation_Ask:
                    selectedfragment = ASkFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.framelayout, selectedfragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
