package com.valentine.NewsReader.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ASkFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Article> list=new ArrayList<>();
    View rootview;
    public ASkFragment() {}

    public static ASkFragment newInstance() {
        ASkFragment fragment = new ASkFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.ask_fragment, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerviewask);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        final ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);

        apiInterface.getAskStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                List<Integer> topStories = response.body();
                for (int i = 0; i < 10; i++) {
                    apiInterface.getArticle(topStories.get(i)).enqueue(new Callback<ArticleResponse>() {
                        @Override
                        public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                            String title = response.body().getTitle().toString();
                            String url = response.body().getUrl();
                            String type = response.body().getType();
                            list.add(new Article(title, url,type));                            NewsAdapter adapter = new NewsAdapter(list);
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

        return rootview;
    }
}
