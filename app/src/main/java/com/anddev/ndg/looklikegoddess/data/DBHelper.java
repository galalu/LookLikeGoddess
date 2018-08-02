package com.anddev.ndg.looklikegoddess.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        values.put(TipsContract.TipsEntry.COLUMN_TIP_TEXT , "My text");

        sqLiteDatabase.insert(TipsContract.TipsEntry.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TipsContract.TipsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
