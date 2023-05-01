package com.ubaid.nytimesnews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ubaid.nytimesnews.models.MediaMetadatum;
import com.ubaid.nytimesnews.models.Medium;
import com.ubaid.nytimesnews.models.ModelNews;

import com.ubaid.nytimesnews.models.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String TAG = "NewsAdapter";
    private Context context;
    ArrayList<ModelNews> list;
    ModelNews model;
    List<Result> results;
    List<Medium> media;
    List<MediaMetadatum> mediaMetadata;

    public NewsAdapter(Context context, ModelNews list) {
        this.context = context;
        this.model = list;
        this.results =  model.getResults();
        /*this.media = model.getResults().get(0).getMedia();*/
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_items, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (model !=null){
            holder.title.setText(results.get(position).getTitle());
            holder.date.setText(results.get(position).getPublishedDate());
            holder.except.setText(results.get(position).getAbstract());
        }

        String url = results.get(position).getMedia().get(0).getMediaMetadata().get(2).getUrl();

            Glide.with(context)
                .load(url)
                    .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        if (results.size() > 0){
            return results.size();
        }else{
            return 0;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView thumbnail;
        TextView title, except, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.news_title);
            except = itemView.findViewById(R.id.news_excerpt);
            date = itemView.findViewById(R.id.news_date);
        }
    }

}
