package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class DateView extends LinearLayout {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());

    private final ISODateView isoDateView;
    private final TimeView freeDateView;

    public DateView(Context context) {
        super(context);
        setOrientation(VERTICAL);

        setBackgroundColor(0xffffffff);

        add(isoDateView = new ISODateView(context));
        add(freeDateView = new TimeView(context));
    }

    public DateView set(Date date) {
        String dateString = formatter.format(date);
        isoDateView.set(dateString);
        freeDateView.set(dateString);
        return this;
    }

    public String get() {
        return freeDateView.getText().toString();
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

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        freeDateView.setWidth(isoDateView.getMeasuredWidth());
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

        public void set(String dateString) {
            year.setText(dateString.substring(0, 4));
            month.setText(dateString.substring(4, 6));
            day.setText(dateString.substring(6, 8));
            hour.setText(dateString.substring(8, 10));
            minute.setText(dateString.substring(10, 12));
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

        public void set(String dateString) {
            setText(dateString);
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
