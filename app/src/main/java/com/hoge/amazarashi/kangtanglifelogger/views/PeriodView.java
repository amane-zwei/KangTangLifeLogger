package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoge.amazarashi.kangtanglifelogger.KTLLTheme;

import java.util.Date;

import lombok.Getter;

public class PeriodView extends LinearLayout {
    @Getter
    private final DateView from;
    @Getter
    private final DateView to;

    public PeriodView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);

        setGravity(Gravity.CENTER_VERTICAL);

        add(from = new DateView(context).set(new Date(System.currentTimeMillis())));
        {
            TextView label = new TextView(context);
            label.setTextColor(KTLLTheme.textColor);
            label.setText("-");
            add(label);
        }
        add(to = new DateView(context));
    }

    public void add(View view) {
        view.setLayoutParams(
                new LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        addView(view);
    }
}
