package com.valentine.NewsReader.UI;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.valentine.NewsReader.Model.Article;
import com.valentine.NewsReader.R;

import java.util.ArrayList;

public class TopStories extends NewsActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Article> list = new ArrayList<>();
    RelativeLayout newscontainer;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newscontainer = (RelativeLayout) findViewById(R.id.newscontainer);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        View wizard = getLayoutInflater().inflate(R.layout.activity_top_stories, null);
        newscontainer.addView(wizard);

    }
}