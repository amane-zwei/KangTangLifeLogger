package com.hoge.amazarashi.kangtanglifelogger.util;

import android.content.Context;

public class DisplayMetricsUtil {
    public static int calcPixel(Context context, int dp) {
        return (int)(context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    public static int calcWidth(Context context, int percent) {
        return context.getResources().getDisplayMetrics().widthPixels * percent / 100;
    }

    public static int calcHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}