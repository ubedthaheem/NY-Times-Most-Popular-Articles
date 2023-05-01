package com.ubaid.nytimesnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ubaid.nytimesnews.R;
import com.ubaid.nytimesnews.models.MediaMetadatum;
import com.ubaid.nytimesnews.models.Medium;
import com.ubaid.nytimesnews.models.ModelNews;

import com.ubaid.nytimesnews.models.Result;
import com.ubaid.nytimesnews.utils.TapListener;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String TAG = "NewsAdapter";
    private Context context;
    ArrayList<ModelNews> list;
    ModelNews model;
    List<Result> results;
    private TapListener tapListener;


    public NewsAdapter(Context context, ModelNews list) {
        this.context = context;
        this.model = list;
        this.results =  model.getResults();
    }

    public void onNewsClick (TapListener listener){
        this.tapListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_items_grid, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(results.get(position).getTitle());
        holder.date.setText(results.get(position).getPublishedDate());
        holder.except.setText(results.get(position).getAbstract());
        holder.section.setText(results.get(position).getSection());

        String url = results.get(position).getMedia().get(0).getMediaMetadata().get(2).getUrl();

        Glide.with(context)
                .load(url)
                .into(holder.thumbnail);

        holder.cardView.setOnClickListener(v -> {
            if (tapListener !=null){
                tapListener.OnTapView(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (model !=null && results.size() > 0){
            return results.size();
        }else{
            return 0;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView thumbnail;
        TextView title, except, date, section;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.news_title);
            except = itemView.findViewById(R.id.news_excerpt);
            date = itemView.findViewById(R.id.news_date);
            section = itemView.findViewById(R.id.section_name);
            cardView = itemView.findViewById(R.id.news_card);
        }
    }

}
