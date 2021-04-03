package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;

import java.util.ArrayList;
import java.util.List;

public class InputView extends LinearLayout {

    private final Button button;
    private final List<InputItemView> items;

    public InputView(Context context) {
        super(context);

        this.setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(0xffc0c0ff);

        items = new ArrayList<>();

        addItem(context);

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
        KTLLEvent event = new KTLLEvent("sample_title");
        KTLLAction action = new KTLLAction("20210101");
        event.add(action);
        for (int index = 0; index < items.size(); index++) {
            action.add(items.get(index).generateTag());
        }
        return event;
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
        items.add(item);
    }

    public interface OnSaveListener {
        void onSave(KTLLEvent event);
    }
}
