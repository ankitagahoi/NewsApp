package com.example.newsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.model.Article;
import com.example.newsapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private TextView titleTextView, descTextView;
    private ImageView detailsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleTextView = findViewById(R.id.detailsTitleTextView);
        descTextView = findViewById(R.id.detailsDescTextView);
        detailsImageView = findViewById(R.id.detailsImageView);

        Intent receivedIntent = getIntent();
        String articleData = receivedIntent.getStringExtra("article");
        Gson gson = new Gson();
        Article article = gson.fromJson(articleData, Article.class);
        titleTextView.setText(article.getTitle());
        descTextView.setText(article.getDescription());
        String photoUrl = article.getUrlToImage();

        if (photoUrl == null) {
           detailsImageView.setImageResource(R.drawable.no_image_available);
        } else {
            Picasso.get().load(photoUrl).into(detailsImageView);
        }
    }

}
