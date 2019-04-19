package com.example.newsapp.network;

import com.example.newsapp.Constants;
import com.example.newsapp.network.response.ArticleResponse;
import com.example.newsapp.network.response.SourcesResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface APIService {
    @GET(Constants.SOURCES_URL)
    Call<SourcesResponse> getSources(@QueryMap Map<String, String> query);

    @GET(Constants.TOP_HEADLINES_URL)
    Call<ArticleResponse> getTopHeadlines(@QueryMap Map<String, String> query);
}

