package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoge.amazarashi.kangtanglifelogger.R;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.fragments.dialogs.ExportDialog;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;

public class InputView extends CoordinatorLayout {

    private final FloatingActionButton button;

    private PeriodView periodView;
    private ScrollValuesView scrollValuesView;

    public InputView(Context context) {
        super(context);

        final int mainViewId = generateViewId();

        setBackgroundColor(0xffc0c0ff);

        {
            Button button = new Button(context);
            button.setText("三");
            button.setOnClickListener(v -> new ExportDialog()
                    .show(((AppCompatActivity) context).getSupportFragmentManager(), "menu"));
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setAnchorId(mainViewId);
            layoutParams.anchorGravity = Gravity.TOP | Gravity.LEFT;
            button.setLayoutParams(layoutParams);
            this.addView(button);
        }
        {
            View mainView = createMainView(context);
            mainView.setId(mainViewId);
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
            mainView.setLayoutParams(layoutParams);
            this.addView(mainView);
        }
        {
            FloatingActionButton button = this.button = new FloatingActionButton(context);
            button.setImageResource(R.drawable.ic_baseline_add_24);
            button.setBackgroundTintList(ColorStateList.valueOf(0xffffa0a0));
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setAnchorId(mainViewId);
            layoutParams.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT | Gravity.END;
            button.setLayoutParams(layoutParams);
            this.addView(button);
        }
    }

    private View createMainView(Context context) {
        final int marginH = DisplayMetricsUtil.calcPixel(context, 16);
        final int marginV = DisplayMetricsUtil.calcPixel(context, 8);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        {
            PeriodView periodView = this.periodView = new PeriodView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(marginH, marginV, marginH, marginV);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            periodView.setLayoutParams(layoutParams);
            layout.addView(periodView);
        }
        {
            scrollValuesView = new ScrollValuesView(context);
            scrollValuesView.add();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    0,
                    1);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            scrollValuesView.setLayoutParams(layoutParams);
            layout.addView(scrollValuesView);
        }
        return layout;
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
