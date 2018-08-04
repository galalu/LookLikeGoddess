package com.anddev.ndg.looklikegoddess;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.anddev.ndg.looklikegoddess.data.TipsContract;

public class TipsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = TipsActivity.class.getSimpleName();
    private static final int LOADER_ID = 0x01;
    private Toolbar mToolbar;
    private TextView tipsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        tipsTextView = findViewById(R.id.tvTips);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private Cursor getAllTips() {
        return getContentResolver().query(TipsContract.TipsEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, TipsContract.TipsEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {

            String tipsTextString = cursor.getString(1);
            tipsTextView.setText(tipsTextString);
            LookLikeGoddessIntentService.startActionUpdateTips(TipsActivity.this, tipsTextString);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}