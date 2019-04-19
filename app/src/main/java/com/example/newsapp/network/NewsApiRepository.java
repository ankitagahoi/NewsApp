package com.example.newsapp.network;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import com.example.newsapp.Constants;
import com.example.newsapp.model.Article;
import com.example.newsapp.network.response.ArticleResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsApiRepository {

    private MutableLiveData<List<Article>> mutableLiveData;
    private Application application;
    private APIService mAPIService;
    private Map<String, String> query;

    public NewsApiRepository(Application application) {
        this.application = application;
        mAPIService = APIClient.getAPIService();
        query = new HashMap<>();
        query.put("apiKey", Constants.API_KEY);
    }


    public MutableLiveData<List<Article>> getMutableLiveData(){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }

        query.put("country", "in");
        query.put("language", "en");

        mAPIService.getTopHeadlines(query)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        if (response.isSuccessful()){
                            ArticleResponse newsRes = response.body();
                            List<Article> articleList = newsRes.getArticles();
                            mutableLiveData.setValue(articleList);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                    }
                });
        return mutableLiveData;
    }
}