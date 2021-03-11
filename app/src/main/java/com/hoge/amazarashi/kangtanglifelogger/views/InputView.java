package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;

public class InputView extends LinearLayout {

    public InputView(Context context) {
        super(context);

        this.setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(0xffc0c0ff);

        addItem(context);

        {
            Button button = new Button(context);
            button.setText("save");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.gravity = Gravity.CENTER;
            button.setLayoutParams(layoutParams);
            this.addView(button);
        }
    }

    private void addItem(Context context) {
        final int marginH = DisplayMetricsUtil.calcPixel(context, 16);
        final int marginTop = DisplayMetricsUtil.calcPixel(context, 8);

        InputItemView item = new InputItemView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(marginH, marginTop, marginH, 0);
        item.setLayoutParams(layoutParams);
        this.addView(item);
    }
}
