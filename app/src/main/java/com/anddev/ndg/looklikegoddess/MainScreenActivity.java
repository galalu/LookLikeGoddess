package com.anddev.ndg.looklikegoddess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainScreenActivity extends AppCompatActivity {

    private CardView cardMealPlan;

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
    }
}
