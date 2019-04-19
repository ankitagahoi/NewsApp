package com.example.newsapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.newsapp.model.Article;
import com.example.newsapp.network.NewsApiRepository;

import java.util.List;

public class NewsActivityViewModel extends AndroidViewModel {

    NewsApiRepository newsRepository;

    public NewsActivityViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsApiRepository(application);
    }

    public LiveData<List<Article>> getTopHeadlines(){
        return newsRepository.getMutableLiveData();
    }




}
