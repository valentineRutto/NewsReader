package com.valentine.NewsReader.REST;

import com.valentine.NewsReader.Model.ArticleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by valentine on 19-05-2018.
 */

public interface ApiInterface {
    @GET("/v0/topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();



    @GET("v0/showstories.json?print=pretty")
    Call<List<Integer>> getShowStories();

    @GET("/v0/jobstories.json?print=pretty")
    Call<List<Integer>> getJobStories();

    @GET("/v0/askstories.json?print=pretty")
    Call<List<Integer>> getAskStories();

    @GET("v0/item/{articleid}.json?print=pretty")
    Call<ArticleResponse> getArticle(@Path("articleid") int id);
}
