package com.ubaid.nytimesnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ubaid.nytimesnews.adapters.NewsAdapter;
import com.ubaid.nytimesnews.databinding.ActivityMainBinding;
import com.ubaid.nytimesnews.models.ModelNews;
import com.ubaid.nytimesnews.models.Result;
import com.ubaid.nytimesnews.services.NewsApi;
import com.ubaid.nytimesnews.services.Service;
import com.ubaid.nytimesnews.utils.Credentials;
import com.ubaid.nytimesnews.utils.TapListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<ModelNews> modelArrayList;
    private ArrayList<Result> results;
    private NewsAdapter adapter;
    private ModelNews listModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NewsApi newsApi = new Service().getNewsApi();

        Call<ModelNews> newsCall = newsApi.getData(Credentials.API_KEY);


        newsCall.enqueue(new Callback<ModelNews>() {
            @Override
            public void onResponse(Call<ModelNews> call, Response<ModelNews> response) {
                // check the response
                if (response.code() == 200){
                    // not OKAY response
                    listModel = response.body();
                    if (listModel != null) {
                        adapter = new NewsAdapter(getApplicationContext(), listModel);
                        binding.mainRecyclerView.setAdapter(adapter);
                        binding.messageTv.setVisibility(View.GONE);
                        binding.mainRecyclerView.setVisibility(View.VISIBLE);
                        binding.progressBar.setVisibility(View.GONE);

                        openDetails();
                    }



                }else{
                    // show toast &
                    binding.messageTv.setText("Failed to load data \n" +response.code() );
                    binding.progressBar.setVisibility(View.GONE);
                    binding.messageTv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ModelNews> call, Throwable t) {
                // response failed
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void openDetails(){
        if (listModel !=null){
            List<Result> results = listModel.getResults();
            adapter.onNewsClick(position -> {
                String title = results.get(position).getTitle();
                String date = results.get(position).getPublishedDate();
                String excerpt = results.get(position).getAbstract();
                String section = results.get(position).getSection();
                String thumbnail = results.get(position).getMedia().get(0).getMediaMetadata().get(2).getUrl();
                String by_line = results.get(position).getByline();
                String web_url = results.get(position).getUrl();
                String thumb_caption = results.get(position).getMedia().get(0).getCaption();

                // open activity
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("excerpt", excerpt);
                intent.putExtra("section", section);
                intent.putExtra("thumbnail", thumbnail);
                intent.putExtra("by_line", by_line);
                intent.putExtra("web_url", web_url);
                intent.putExtra("thumb_caption", thumb_caption);
                intent.putExtra("date", date);
                startActivity(intent);
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}