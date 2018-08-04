package com.anddev.ndg.looklikegoddess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.anddev.ndg.looklikegoddess.data.TipsContract;

public class MainScreenActivity extends AppCompatActivity {

    private CardView cardMealPlan;
    private CardView cardTips;
    private CardView cardPreWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        cardMealPlan = findViewById(R.id.cardMealPlan);
        cardMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreenActivity.this, MealPlanActivity.class);
                startActivity(intent);

            }
        });

        cardTips = findViewById(R.id.cardTipOfDay);
        cardTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreenActivity.this, TipsActivity.class);
                startActivity(intent);
            }
        });

        cardPreWorkout = findViewById(R.id.cardPreWorkout);
        cardPreWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreenActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
