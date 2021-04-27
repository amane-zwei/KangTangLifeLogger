package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.fragments.dialogs.ExportDialog;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;

public class InputView extends LinearLayout {

    private final Button button;

    private final PeriodView periodView;
    private final ScrollValuesView scrollValuesView;

    public InputView(Context context) {
        super(context);

        final int marginH = DisplayMetricsUtil.calcPixel(context, 16);
        final int marginV = DisplayMetricsUtil.calcPixel(context, 8);

        this.setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(0xffc0c0ff);
        setGravity(Gravity.CENTER_HORIZONTAL);

        {
            Button button = new Button(context);
            button.setText("三");
            button.setOnClickListener(v -> new ExportDialog()
                    .show(((AppCompatActivity) context).getSupportFragmentManager(), "menu"));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.gravity = Gravity.LEFT;
            button.setLayoutParams(layoutParams);
            this.addView(button);
        }

        {
            PeriodView periodView = this.periodView = new PeriodView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(marginH, marginV, marginH, marginV);
            periodView.setLayoutParams(layoutParams);
            this.addView(periodView);
        }
        {
            scrollValuesView = new ScrollValuesView(context);
            scrollValuesView.add();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    0,
                    1);
            scrollValuesView.setLayoutParams(layoutParams);
            this.addView(scrollValuesView);
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
        for (Value value : scrollValuesView.generateValues()) {
            action.add(value);
        }
        return event;
    }

    public interface OnSaveListener {
        void onSave(KTLLEvent event);
    }
}
