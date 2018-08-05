package com.anddev.ndg.looklikegoddess.network;

import com.anddev.ndg.looklikegoddess.Exercise;
import com.anddev.ndg.looklikegoddess.models.ExerciseImage;
import com.anddev.ndg.looklikegoddess.models.ExerciseImageList;
import com.anddev.ndg.looklikegoddess.models.ExerciseList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExercisesAPI {
    @GET("exercise/")
    Call<ExerciseList> getExercises(@Query("language") int languge, @Query("status") int status);

    @GET("exercise/{id}/")
    Call<Exercise> getExercise(@Path("id") int id);

    @GET("exerciseimage/")
    Call<ExerciseImageList> getImageForExercise(@Query("exercise") int exercise_id);


}
