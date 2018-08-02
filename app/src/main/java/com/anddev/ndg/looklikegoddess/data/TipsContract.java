package com.anddev.ndg.looklikegoddess.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class TipsContract {
    public static final String AUTHORITY = "com.anddev.ndg.looklikegoddess";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_TIPS = "tips";


    public static final class TipsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TIPS).build();

        public static final String TABLE_NAME = "tips";
        public static final String _ID = "id";
        public static final String COLUMN_POSTER_PATH = "tipText";

    }

}
