package com.example.newsapp.activities;

import com.example.newsapp.model.Article;

public interface NewsListCallback {
    void onArticleClicked(Article article);
}
