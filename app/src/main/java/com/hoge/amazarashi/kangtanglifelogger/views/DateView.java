package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DateView extends LinearLayout {
    private final ISODateView isoDateView;
    private final TimeView freeDateView;

    public DateView(Context context) {
        super(context);
        setOrientation(VERTICAL);

        add(isoDateView = new ISODateView(context));
        add(freeDateView = new TimeView(context));
    }

    public void add(View view) {
        view.setLayoutParams(
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        addView(view);
    }

    private static class ISODateView extends LinearLayout {
        private final TimeView year;
        private final TimeView month;
        private final TimeView day;
        private final TimeView hour;
        private final TimeView minute;

        public ISODateView(Context context) {
            super(context);
            setOrientation(HORIZONTAL);

            add(year = new TimeView(context));
            add(new LabelView(context).setLabel("-"));
            add(month = new TimeView(context));
            add(new LabelView(context).setLabel("-"));
            add(day = new TimeView(context));
            add(new LabelView(context).setLabel(" "));
            add(hour = new TimeView(context));
            add(new LabelView(context).setLabel(":"));
            add(minute = new TimeView(context));
        }

        public void add(View view) {
            view.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );
            addView(view);
        }

    }

    private static class TimeView extends androidx.appcompat.widget.AppCompatEditText {
        public TimeView(Context context) {
            super(context);
        }
    }

    private static class LabelView extends androidx.appcompat.widget.AppCompatTextView {
        public LabelView(Context context) {
            super(context);
        }

        public LabelView setLabel(String text) {
            setText(text);
            return this;
        }
    }
}
