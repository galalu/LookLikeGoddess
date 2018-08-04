package com.anddev.ndg.looklikegoddess;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import java.util.ArrayList;


public class LookLikeGoddessIntentService extends IntentService {
    public static final String ACTION_GET_TIP =
            "com.anddev.ndg.looklikegoddess.action.get_tip";
    public static final String EXTRA_TIP="EXTRA_TIP";
    public static final String ACTION_UPDATE_WIDGET = "com.anddev.ndg.looklikegoddess.action.wiget_update";

    public LookLikeGoddessIntentService() {
        super("IngredientsService");
    }

    public static void startActionGetTips(Context context) {
        Intent intent = new Intent(context, LookLikeGoddessIntentService.class);
        intent.setAction(ACTION_GET_TIP);
        context.startService(intent);
    }

    public static void startActionUpdateTips(Context context, String string) {
        Intent intent = new Intent(context, LookLikeGoddessIntentService.class);
        intent.putExtra(EXTRA_TIP, string);
        intent.setAction(ACTION_UPDATE_WIDGET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_TIP.equals(action)) {
                handleActionGetRecipe();
            } else if (ACTION_UPDATE_WIDGET.equals(action)) {
                handleActionUpdateWidget(intent.getStringExtra(EXTRA_TIP));
            }
        }
    }

    private void handleActionUpdateWidget(String tip) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, LookLikeGoddessWidgetProvider.class));

        LookLikeGoddessWidgetProvider.onUpdateWidget(this, appWidgetManager, tip, appWidgetIds);

    }

    private void handleActionGetRecipe() {

        final RemoteViews views = new RemoteViews(getApplicationContext().getPackageName(), R.layout.look_like_goddess_widget_provider);


    }
}
