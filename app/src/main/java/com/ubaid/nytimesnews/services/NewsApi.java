package com.ubaid.nytimesnews.services;

import androidx.cardview.widget.CardView;

import com.ubaid.nytimesnews.models.ModelNews;
import com.ubaid.nytimesnews.models.Result;
import com.ubaid.nytimesnews.utils.Credentials;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewsApi {
    //@Headers("api-key: " + Credentials.API_KEY)
    @GET("mostviewed/all-sections/7.json?api-key=G4o88ZOJ0k9z2sESF5vFhfCNj0VzwNli")
    Call<ModelNews> getData();

    @GET("viewed/7.json")
    Call<Result> getAppResult();

    
}
