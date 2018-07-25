package com.anddev.ndg.looklikegoddess;

import android.app.Application;

import com.anddev.ndg.looklikegoddess.network.ExercisesAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LLGApp extends Application {
    private static ExercisesAPI exercisesAPI;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        exercisesAPI = retrofit.create(ExercisesAPI.class);
    }

    public static ExercisesAPI getApi() {
        return exercisesAPI;
    }
}
