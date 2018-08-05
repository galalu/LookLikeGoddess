package com.anddev.ndg.looklikegoddess.models;

import com.anddev.ndg.looklikegoddess.Exercise;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExerciseList {

        @SerializedName("results")
        public List<Exercise> exerciseList;
        // ...

}
