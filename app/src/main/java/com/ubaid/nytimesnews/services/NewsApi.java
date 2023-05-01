package com.ubaid.nytimesnews.services;

import androidx.cardview.widget.CardView;

import com.ubaid.nytimesnews.models.ModelNews;
import com.ubaid.nytimesnews.models.Result;
import com.ubaid.nytimesnews.utils.Credentials;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsApi {
    //@Headers("api-key: " + Credentials.API_KEY)
    @GET("mostviewed/all-sections/7.json")
    Call<ModelNews> getData(@Query("api-key") String api_key);

    @GET("viewed/7.json")
    Call<Result> getAppResult();

    
}
