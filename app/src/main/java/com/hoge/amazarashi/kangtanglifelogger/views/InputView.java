package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoge.amazarashi.kangtanglifelogger.R;
import com.hoge.amazarashi.kangtanglifelogger.fragments.dialogs.ExportDialog;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService.EventRecord;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;
import com.hoge.amazarashi.kangtanglifelogger.viewmodel.EventViewModel;

public class InputView extends CoordinatorLayout {

    private final FloatingActionButton button;

    private PeriodView periodView;
    private ScrollValuesView scrollValuesView;
    private EventViewModel eventViewModel;

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
        this.addView(this.button = createFloatingButton(
                context,
                mainViewId,
                R.drawable.ic_baseline_add_24,
                Gravity.BOTTOM | Gravity.RIGHT));
        {
            FloatingActionButton button = createFloatingButton(
                    context,
                    mainViewId,
                    R.drawable.ic_baseline_add_24,
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            button.setOnClickListener(this::onAddValue);
            addView(button);
        }
    }

    public void attacheEventViewModel(EventViewModel eventViewModel) {
        scrollValuesView.setAdapter(eventViewModel.getAdapter());
        this.eventViewModel = eventViewModel;
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
            layoutParams.setMargins(marginH, marginV, marginH, 0);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            periodView.setLayoutParams(layoutParams);
            layout.addView(periodView);
        }
        {
            scrollValuesView = new ScrollValuesView(context);
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

    private FloatingActionButton createFloatingButton(
            Context context,
            int mainViewId,
            @DrawableRes int drawable,
            int gravity) {
        FloatingActionButton button = new FloatingActionButton(context);
        button.setImageResource(drawable);
        button.setBackgroundTintList(ColorStateList.valueOf(0xffffa0a0));
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setAnchorId(mainViewId);
        layoutParams.anchorGravity = gravity;
        button.setLayoutParams(layoutParams);
        return button;
    }

    public void setOnSaveListener(OnSaveListener listener) {
        button.setOnClickListener((View view) -> listener.onSave(generateEvent()));
    }

    private void onAddValue(View view) {
        eventViewModel.addValue();
    }

//    public InputValueView restore(EventViewModel.ValueViewModel valueViewModel) {
//        InputValueView inputValueView = scrollValuesView.add();
//        inputValueView.applyValue(valueViewModel);
//        return inputValueView;
//    }

//    @androidx.annotation.RequiresPermission(anyOf = {
//            "android.permission.ACCESS_COARSE_LOCATION",
//            "android.permission.ACCESS_FINE_LOCATION"})
//    public InputValueView addLocation() {
//        InputValueView inputValueView = scrollValuesView.addLocation();
//        return inputValueView.setValueRecord(valueProvider.get(inputValueView));
//    }

    private EventRecord generateEvent() {
        return null;
//        return new EventRecord(
//                new KTLLEvent(),
//                new KTLLAction(periodView.getFrom().get()),
//                scrollValuesView.getValues()
//        );
    }

    public interface OnSaveListener {
        void onSave(EventRecord event);
    }
}
