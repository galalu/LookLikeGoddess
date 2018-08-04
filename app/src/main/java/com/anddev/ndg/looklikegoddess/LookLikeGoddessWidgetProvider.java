package com.anddev.ndg.looklikegoddess;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class LookLikeGoddessWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, String tip, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.look_like_goddess_widget_provider);
        views.setTextViewText(R.id.appwidget_text, tip);

               // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        LookLikeGoddessIntentService.startActionGetTips(context);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.look_like_goddess_widget_provider);

        appWidgetManager.updateAppWidget(appWidgetIds, views);
//        }
    }

    public static void onUpdateWidget(Context context, AppWidgetManager appWidgetManager, String tip, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context,tip, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

