package com.anddev.ndg.looklikegoddess;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

        }
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
                    Log.d("TAG", t.getMessage());
                    Toast.makeText(ExersicesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
