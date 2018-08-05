package com.anddev.ndg.looklikegoddess;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anddev.ndg.looklikegoddess.models.ExerciseImage;
import com.anddev.ndg.looklikegoddess.models.ExerciseImageList;
import com.anddev.ndg.looklikegoddess.models.ExerciseList;
import com.anddev.ndg.looklikegoddess.ui.CardItem;
import com.anddev.ndg.looklikegoddess.ui.CardPagerAdapter;
import com.anddev.ndg.looklikegoddess.ui.ShadowTransformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExersicesActivity extends AppCompatActivity {
    private CardPagerAdapter mCardAdapter;
    private ViewPager mViewPager;
    private String image;
    public static final String EXERCISES_ARRAY_EXTRA = "EXERCISES_ARRAY_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exersices);
        mCardAdapter = new CardPagerAdapter();
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        if (getIntent().getExtras() != null) {
            ArrayList<Integer> exercisesIdsList = getIntent().getIntegerArrayListExtra(EXERCISES_ARRAY_EXTRA);
            loadExercisesByIds(exercisesIdsList);
            Toast.makeText(ExersicesActivity.this, exercisesIdsList.get(0).toString(), Toast.LENGTH_LONG).show();
        }

        // loadExercises();


    }

    private void loadExercisesByIds(ArrayList<Integer> exercisesIdsList) {

        for (int i = 0; i < exercisesIdsList.size(); i++) {
            LLGApp.getApi().getExercise(exercisesIdsList.get(i).intValue()).enqueue(new Callback<com.anddev.ndg.looklikegoddess.Exercise>() {
                @Override
                public void onResponse(Call<com.anddev.ndg.looklikegoddess.Exercise> call, Response<com.anddev.ndg.looklikegoddess.Exercise> response) {
                    final String description = response.body().getDescription();
                    final String name = response.body().getName();
                    LLGApp.getApi().getImageForExercise(response.body().getId()).enqueue(new Callback<ExerciseImageList>() {
                        @Override
                        public void onResponse(Call<ExerciseImageList> call, Response<ExerciseImageList> response) {
                            if (response.body().exerciseImageList.size() > 0) {
                                mCardAdapter.addCardItem(new CardItem(description, name, response.body().exerciseImageList.get(0).getImage()));
                            }
                            else{
                                mCardAdapter.addCardItem(new CardItem(description, name, ""));
                            }
                            ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
                            fragmentCardShadowTransformer.enableScaling(true);

                            mViewPager.setAdapter(mCardAdapter);
                            mViewPager.setPageTransformer(false, fragmentCardShadowTransformer);
                            mViewPager.setOffscreenPageLimit(3);
                        }

                        @Override
                        public void onFailure(Call<ExerciseImageList> call, Throwable t) {
                            Log.d("TAG", t.getMessage());
                        }
                    });


                }

                @Override
                public void onFailure(Call<com.anddev.ndg.looklikegoddess.Exercise> call, Throwable t) {
                    Toast.makeText(ExersicesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    private void loadExercises() {
        LLGApp.getApi().getExercises(2, 2).enqueue(new Callback<ExerciseList>() {
            @Override
            public void onResponse(Call<ExerciseList> call, Response<ExerciseList> response) {
                ArrayList<String> imagesList = new ArrayList<>();
//                for (int i = 0; i < response.body().exerciseList.size(); i++) {
//                    imagesList.add(loadExerciseImages(response.body().exerciseList.get(i).getId()));
//                }
                populateUi(response.body().exerciseList);
            }

            @Override
            public void onFailure(Call<ExerciseList> call, Throwable t) {
                Toast.makeText(ExersicesActivity.this, "failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String loadExerciseImages(int exerciseId) {
        LLGApp.getApi().getImageForExercise(exerciseId).enqueue(new Callback<ExerciseImageList>() {
            @Override
            public void onResponse(Call<ExerciseImageList> call, Response<ExerciseImageList> response) {
                if (response.body() != null) {
                    if (response.body().exerciseImageList.size() > 0) {
                        image = response.body().exerciseImageList.get(0).getImage();
                    } else {
                        image = "https://wger.de/media/exercise-images/4/Crunches-1.png";
                    }
                }
            }

            @Override
            public void onFailure(Call<ExerciseImageList> call, Throwable t) {
                image = "https://wger.de/media/exercise-images/4/Crunches-1.png";
            }
        });


        return image;
    }

    private void populateUiForExercises(CardPagerAdapter cardPagerAdapter) {

        mCardAdapter = cardPagerAdapter;
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void populateUi(List<com.anddev.ndg.looklikegoddess.Exercise> exerciseList) {

        for (int i = 0; i < 10; i++) {
            //     mCardAdapter.addCardItem(new CardItem(exerciseList.get(i).getId().toString(), exerciseList.get(i).getDescription()));

        }

//        mCardAdapter.addCardItem(new CardItem(response.body().exerciseList.get(3).getName(), response.body().exerciseList.get(3).getDescription()));
//        mCardAdapter.addCardItem(new CardItem(response.body().exerciseList.get(4).getName(), response.body().exerciseList.get(4).getDescription()));
//        mCardAdapter.addCardItem(new CardItem(response.body().exerciseList.get(5).getName(), response.body().exerciseList.get(5).getDescription()));

        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }
}
