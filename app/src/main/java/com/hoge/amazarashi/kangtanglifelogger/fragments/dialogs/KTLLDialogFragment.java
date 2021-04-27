package com.hoge.amazarashi.kangtanglifelogger.fragments.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class KTLLDialogFragment extends DialogFragment {
    public static final int BUTTON_CANCEL = 0;
    public static final int BUTTON_POSITIVE = 1;

    private final OnButtonFunction[] onButtonFunctions = new OnButtonFunction[2];
    private final Button[] buttons = new Button[2];

    private static final int margin = 10;

    @Override
    public @NonNull Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(createMainView(getContext()));
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        layoutParams.width = metrics.widthPixels - margin;
        dialog.getWindow().setAttributes(layoutParams);
    }

    public String getTitle() {
        return null;
    }

    public View createContentView(Context context) {
        return new View(context);
    }

    protected void setOnPositiveButton(OnButtonFunction onPositiveButton) {
        onButtonFunctions[1] = onPositiveButton;
    }

    public void setButtonEnabled(int buttonId, boolean enabled) {
        buttons[buttonId].setEnabled(enabled);
    }

    private static final int paddingH = 10;
    private static final int paddingV = 10;

    public View createTitleView(Context context, String title) {
        TextView titleView = new TextView(context);
        if (title == null) {
            titleView.setVisibility(View.GONE);
            return titleView;
        }
        titleView.setText(title);
        titleView.setTextColor(0xff000000);
        titleView.setBackground(new ColorDrawable(0xffc0c0ff));
        titleView.setPadding(paddingH, paddingV, paddingH, paddingV);
        titleView.setOnClickListener((View view) -> view.setVisibility(View.GONE));
        return titleView;
    }

    public View createButtonAreaView(Context context) {
        final int margin = 10;

        final String[] texts = {"Cancel", "OK"};

        LinearLayout buttonAreaLayout = new LinearLayout(context);
        buttonAreaLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonAreaLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        for (int index = 0; index < texts.length; index++) {
            Button button = buttons[index] = new Button(context);
            final OnButtonFunction onButtonFunction = onButtonFunctions[index];
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
            layoutParams.setMargins(margin, 0, 0, 0);
            button.setLayoutParams(layoutParams);
            button.setText(texts[index]);
            button.setOnClickListener((View v) -> {
                if (onButtonFunction == null || onButtonFunction.apply()) {
                    dismiss();
                }
            });
            buttonAreaLayout.addView(button);
        }
        return buttonAreaLayout;
    }

    private final static int paddingLeft = 10;
    private final static int paddingRight = 10;
    private final static int paddingTop = 10;
    private final static int paddingBottom = 10;

    private static final int buttonMargin = 10;

    public View createMainView(Context context) {

        LinearLayout linearLayout = new LinearLayout(context);

        int titleViewId = View.generateViewId();
        int contentViewId = View.generateViewId();
        int buttonAreaViewId = View.generateViewId();

        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
//        linearLayout.setBackground(new DotRoundRectDrawable(
//                0xfff0f0ff,
//                0xff000000,
//                0x40000000
//        ));
        linearLayout.setPadding(paddingLeft, paddingTop, paddingRight, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        {
            View titleView = createTitleView(context, getTitle());
            titleView.setId(titleViewId);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.topMargin = paddingTop;
            layoutParams.bottomMargin = 10;
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            titleView.setLayoutParams(layoutParams);
            linearLayout.addView(titleView);
        }
        {
            View contentView = createContentView(context);
            contentView.setId(contentViewId);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            );
            contentView.setLayoutParams(layoutParams);
            linearLayout.addView(contentView);
        }
        {
            View buttonAreaView = createButtonAreaView(context);
            buttonAreaView.setId(buttonAreaViewId);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.topMargin = buttonMargin;
            layoutParams.bottomMargin = paddingBottom;
            buttonAreaView.setLayoutParams(layoutParams);
            linearLayout.addView(buttonAreaView);
        }

        return linearLayout;
    }

    public interface OnButtonFunction {
        boolean apply();
    }
}
