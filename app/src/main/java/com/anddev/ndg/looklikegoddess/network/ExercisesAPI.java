package com.anddev.ndg.looklikegoddess.network;

import com.anddev.ndg.looklikegoddess.Exercise;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExercisesAPI {
    @GET("exercise/")
    Call<Exercise> getExercises(@Query("language") String languge, @Query("api_key") String api_key);

}
