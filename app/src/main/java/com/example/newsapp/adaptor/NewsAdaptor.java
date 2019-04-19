package com.example.newsapp.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.model.Article;
import com.example.newsapp.R;
import com.example.newsapp.activities.NewsListCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdaptor extends RecyclerView.Adapter<NewsAdaptor.MyViewHolder> {
    private Context ctx;
    private List<Article> newsList;
    private NewsListCallback callback;

    public NewsAdaptor(Context ctx, List<Article> topHeadlinesList) {
        this.ctx = ctx;
        this.newsList = topHeadlinesList;
        this.callback = (NewsListCallback) ctx;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(ctx).inflate(R.layout.news_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.publishedAt.setText(newsList.get(position).getPublishedAt());
        holder.source.setText(newsList.get(position).getSource().getName());
        holder.title.setText(newsList.get(position).getTitle());
        String photoUrl = newsList.get(position).getUrlToImage();

        if (photoUrl == null) {
            holder.displayImage.setImageResource(R.drawable.no_image_available);

        } else {
            Picasso.get().load(photoUrl).into(holder.displayImage);
        }
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView source;
        TextView publishedAt;
        TextView title;
        ImageView displayImage;
        CardView cardView;

        MyViewHolder(final View itemView) {
            super(itemView);
            source = itemView.findViewById(R.id.sourceTextView);
            publishedAt = itemView.findViewById(R.id.publishedAtTextView);
            title = itemView.findViewById(R.id.titleTextView);
            displayImage = itemView.findViewById(R.id.itemImageView);
            cardView = itemView.findViewById(R.id.listCardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onArticleClicked(newsList.get(getAdapterPosition()));
                }
            });
        }
    }
}

