package com.anddev.ndg.looklikegoddess;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.anddev.ndg.looklikegoddess.data.DBHelper;
import com.anddev.ndg.looklikegoddess.data.TipsContract;
import com.anddev.ndg.looklikegoddess.data.TipsProvider;

public class MealPlanActivity extends AppCompatActivity {
    private TextView mealPlanTextView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        mealPlanTextView = findViewById(R.id.mealPlanTextView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        Cursor tipsText = getAllTips();
        if (tipsText.moveToFirst()) {
            String tipsTextString = tipsText.getString(tipsText.getColumnIndex(TipsContract.TipsEntry.COLUMN_TIP_TEXT));
            mealPlanTextView.setText(tipsTextString);

        }


    }

    private Cursor getAllTips() {
        return getContentResolver().query(TipsContract.TipsEntry.CONTENT_URI, null, null, null, null);
    }
}
