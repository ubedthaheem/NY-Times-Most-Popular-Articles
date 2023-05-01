package com.ubaid.nytimesnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ubaid.nytimesnews.databinding.ActivityMainBinding;
import com.ubaid.nytimesnews.models.ModelNews;
import com.ubaid.nytimesnews.models.Result;
import com.ubaid.nytimesnews.services.NewsApi;
import com.ubaid.nytimesnews.services.Service;
import com.ubaid.nytimesnews.utils.Credentials;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<ModelNews> modelArrayList;
    private ArrayList<Result> results;
    private NewsAdapter adapter;


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
                    ModelNews listModel = response.body();
                    if (listModel != null) {
                        adapter = new NewsAdapter(getApplicationContext(), listModel);
                        binding.mainRecyclerView.setAdapter(adapter);
                        binding.messageTv.setVisibility(View.GONE);
                        binding.mainRecyclerView.setVisibility(View.VISIBLE);
                        binding.progressBar.setVisibility(View.GONE);
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
}