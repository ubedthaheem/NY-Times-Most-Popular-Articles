package com.ubaid.nytimesnews.services;

import com.ubaid.nytimesnews.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    private static Retrofit retrofit = builder.build();

    private static NewsApi newsApi = retrofit.create(NewsApi.class);

    public NewsApi getNewsApi(){
        return newsApi;
    }

}
