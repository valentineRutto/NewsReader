package com.valentine.NewsReader;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.valentine.NewsReader.UI.NewsActivity;

public class Showstories extends NewsActivity {
 RelativeLayout newscontainer;
 BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_showstories);


        //dynamically include the  current activity      layout into  baseActivity layout.now all the view of baseactivity is   accessible in current activity.
        newscontainer = (RelativeLayout)  findViewById(R.id.newscontainer);
        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        View wizard = getLayoutInflater().inflate(R.layout.activity_showstories, null);
        newscontainer.addView(wizard);


    }
}
