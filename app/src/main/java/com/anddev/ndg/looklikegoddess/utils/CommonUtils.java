package com.anddev.ndg.looklikegoddess.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DimenRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CommonUtils {
    public static int convertDpToPixels(int dp) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int convertSpToPixels(@DimenRes int textSize, Context context) {
        Resources resources = context.getResources();
        int px = (int)(resources.getDimensionPixelSize(textSize) / resources.getDisplayMetrics().density);
        return px;
    }


    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void hideSoftKeyboard(View inputField) {
        InputMethodManager imm = (InputMethodManager)inputField.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputField.getWindowToken(), 0);
    }

    public static void showSoftInput(Activity activity, View inputView) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(inputView, InputMethodManager.SHOW_IMPLICIT);
    }
}
