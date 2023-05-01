package com.ubaid.nytimesnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ubaid.nytimesnews.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.aboutAppBackBtn.setOnClickListener(v -> onBackPressed());
        intent = getIntent();
        if (intent !=null){
            load_views();
        }
    }


    private void load_views(){
        Glide.with(this).load(intent.getStringExtra("thumbnail")).into(binding.imageView);
        binding.newsDetail.setText(getText("excerpt"));
        binding.newsTitle.setText(getText("title"));

        binding.newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getText("web_url")));
                startActivity(i);

            }
        });

    }

    private String getText(String from){
        return intent.getStringExtra(from);
    }
}