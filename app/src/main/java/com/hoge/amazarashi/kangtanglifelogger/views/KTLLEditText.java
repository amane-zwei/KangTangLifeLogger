package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

public class KTLLEditText extends androidx.appcompat.widget.AppCompatEditText {

    public KTLLEditText(Context context) {
        super(context);

        setSelectAllOnFocus(true);

        final GradientDrawable background = new GradientDrawable();
        background.setStroke(2, 0xff000000);
        background.setCornerRadius(5);
        setBackground(background);

        setIncludeFontPadding(false);
        setPadding(5, 5, 5, 5);
    }
}