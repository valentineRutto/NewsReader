package com.valentine.NewsReader.UI;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.valentine.NewsReader.Adapter.NewsAdapter;
import com.valentine.NewsReader.Model.Article;
import com.valentine.NewsReader.Model.ArticleResponse;
import com.valentine.NewsReader.R;
import com.valentine.NewsReader.REST.ApiClient;
import com.valentine.NewsReader.REST.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Showstories extends NewsActivity {
    RelativeLayout newscontainer;
    BottomNavigationView navigation;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Article> list = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_showstories);


        //dynamically include the  current activity      layout into  baseActivity layout.now all the view of baseactivity is   accessible in current activity.
        newscontainer = (RelativeLayout) findViewById(R.id.newscontainer);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        View wizard = getLayoutInflater().inflate(R.layout.activity_showstories, null);
        newscontainer.addView(wizard);

//        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclershow);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);

        apiInterface.getShowStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                List<Integer> topStories = response.body();
                for (int i = 0; i < 10; i++) {
                    apiInterface.getArticle(topStories.get(i)).enqueue(new Callback<ArticleResponse>() {
                        @Override
                        public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                            String title = response.body().getTitle().toString();
                            String url = response.body().getUrl().toString();
                            list.add(new Article(title, url));
                            NewsAdapter adapter = new NewsAdapter(list);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<ArticleResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });


    }
}
