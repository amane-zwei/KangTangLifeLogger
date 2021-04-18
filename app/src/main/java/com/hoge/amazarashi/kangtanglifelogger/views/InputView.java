package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hoge.amazarashi.kangtanglifelogger.entities.Item;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;

public class InputView extends LinearLayout {

    private final Button button;

    private final PeriodView periodView;
    private final TagView tagView;

    public InputView(Context context) {
        super(context);

        this.setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(0xffc0c0ff);
        setGravity(Gravity.CENTER_HORIZONTAL);

        addView(
                context,
                this.periodView = new PeriodView(context),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        {
            tagView = new TagView(context);
            tagView.add();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    0,
                    1);
            tagView.setLayoutParams(layoutParams);
            this.addView(tagView);
        }

        {
            Button button = this.button = new Button(context);
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

    public void setOnSaveListener(OnSaveListener listener) {
        button.setOnClickListener((View view) -> listener.onSave(generateEvent()));
    }

    private KTLLEvent generateEvent() {
        KTLLEvent event = new KTLLEvent();
        KTLLAction action = new KTLLAction(periodView.getFrom().get());
        event.add(action);
        for (Value value : tagView.generate()) {
            action.add(value);
        }
        return event;
    }

    private void addView(Context context, View view, int width, int height) {
        final int marginH = DisplayMetricsUtil.calcPixel(context, 16);
        final int marginTop = DisplayMetricsUtil.calcPixel(context, 8);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                width,
                height);
        layoutParams.setMargins(marginH, marginTop, marginH, 0);
        view.setLayoutParams(layoutParams);
        this.addView(view);
    }

    public interface OnSaveListener {
        void onSave(KTLLEvent event);
    }
}
