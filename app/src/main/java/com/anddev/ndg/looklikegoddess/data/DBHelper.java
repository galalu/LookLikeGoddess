package com.anddev.ndg.looklikegoddess.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tips.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FAVOURITES_TABLE = "CREATE TABLE " +
                TipsContract.TipsEntry.TABLE_NAME + " (" +
                TipsContract.TipsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TipsContract.TipsEntry.COLUMN_TIP_TEXT + " TEXT NOT NULL" + ");";
        sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITES_TABLE);


        ContentValues values = new ContentValues();
        ArrayList<String> tips = getTipsArrayList();
        for (int i = 0; i < tips.size(); i++) {
            values.put(TipsContract.TipsEntry.COLUMN_TIP_TEXT, tips.get(i));
            sqLiteDatabase.insert(TipsContract.TipsEntry.TABLE_NAME, null, values);
        }


    }

    private ArrayList<String> getTipsArrayList() {
        ArrayList<String> tipsArrayList = new ArrayList<>();
        tipsArrayList.add("Exercise Daily");
        tipsArrayList.add("Eat the Right Foods and Portion Each Meal");
        tipsArrayList.add("Keep Track of Calories and Food Intake Per Day");
        tipsArrayList.add("Be Sure to Get Sleep");
        tipsArrayList.add("Pump yourself up. Positive self-talk can boost your motivation. Look in the mirror and observe how strong your muscles are. Applaud yourself for getting fit. Recognize the goals you've met.");
        tipsArrayList.add("Break up your workouts. If you dread a long workout, break it into small chunks. Stretch for 10 minutes before your morning shower. Take a brisk, 20-minute walk at lunch. Lift weights while you wait for your pasta water to boil.");
        tipsArrayList.add("Stretch. It makes you more flexible, relieves muscle tension, and improves posture. It also helps you tune in to your body. No time? No problem. Stretch while watching TV or lying in bed.");
        tipsArrayList.add("Say no to sports drinks. Unless you're a pro athlete, they're not necessary");
        tipsArrayList.add("Challenge yourself. Step it up. Work out 5 minutes longer. Raise the incline level on your treadmill. Go for a few extra repetitions. It makes a difference. ");

        return tipsArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TipsContract.TipsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
