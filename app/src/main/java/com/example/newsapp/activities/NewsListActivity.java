package com.example.newsapp.activities;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsapp.model.Article;
import com.example.newsapp.R;
import com.example.newsapp.adaptor.NewsAdaptor;
import com.example.newsapp.viewmodel.NewsActivityViewModel;
import com.google.gson.Gson;

import java.util.List;

public class NewsListActivity extends AppCompatActivity implements NewsListCallback{
    private RecyclerView newsRecyclerView;
    private NewsAdaptor newsAdaptor;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        //Check if connected to internet
        if(!isNetworkConnected()){
            showNoConnectivityDialog();
            return;
        }

        newsRecyclerView = findViewById(R.id.newsListRecyclerView);
        progressBar = findViewById(R.id.newsListProgressbar);
        progressBar.setIndeterminate(true);

        NewsActivityViewModel activityViewModel = ViewModelProviders.of(this).get(NewsActivityViewModel.class);
        activityViewModel.getTopHeadlines().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                progressBar.setVisibility(View.GONE);
                newsRecyclerView.setLayoutManager(new LinearLayoutManager(NewsListActivity.this, LinearLayoutManager.VERTICAL, false));
                newsAdaptor = new NewsAdaptor(NewsListActivity.this, articles);
                newsRecyclerView.setAdapter(newsAdaptor);

            }
        });
    }

    @Override
    public void onArticleClicked(Article article) {
        Gson gson = new Gson();
        String articleData = gson.toJson(article);
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("article",articleData);
        startActivity(i);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    void showNoConnectivityDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Info");
        alertDialog.setMessage(getString(R.string.no_internet_connection));
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });

        alertDialog.show();
    }
}
