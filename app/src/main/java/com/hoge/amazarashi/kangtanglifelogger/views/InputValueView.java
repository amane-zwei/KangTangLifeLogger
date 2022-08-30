package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hoge.amazarashi.kangtanglifelogger.drawables.DividerDrawable;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;
import com.hoge.amazarashi.kangtanglifelogger.viewmodel.EventViewModel;

import lombok.Getter;

public class InputValueView extends LinearLayout {

    @Getter
    private final TagNameView tagNameView;
    @Getter
    private final ValueView valueView;
    private final Button deleteButton;

    public InputValueView(Context context) {
        super(context);

        final int paddingH = DisplayMetricsUtil.calcPixel(context, 16);
        final int paddingV = DisplayMetricsUtil.calcPixel(context, 8);

        this.setOrientation(LinearLayout.HORIZONTAL);
        setDividerDrawable(new DividerDrawable(5));
        setShowDividers(SHOW_DIVIDER_MIDDLE);

        final GradientDrawable background = new GradientDrawable();
        background.setColor(0xffffffff);
        background.setCornerRadius(5);
        setBackground(background);

        setPadding(paddingH, paddingV, paddingH, paddingV);

        LinearLayout left = new LinearLayout(context);
        left.setOrientation(LinearLayout.VERTICAL);
        left.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1
        ));
        addView(left);

        {
            TagNameView tagNameView = this.tagNameView = new TagNameView(context);
            tagNameView.setBackgroundColor(0xffffa0a0);
            tagNameView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            left.addView(tagNameView);
        }
        {
            ValueView valueView = this.valueView = new ValueView(context);
            valueView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            left.addView(valueView);
        }

        {
            Button button = this.deleteButton = new Button(context);
            button.setText("x");
            button.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.addView(button);
        }
    }

    public void setOnClickDelete(OnClickListener listener) {
        deleteButton.setOnClickListener(listener);
    }

    public void applyViewModel(EventViewModel.ValueViewModel valueViewModel) {
        if (valueViewModel == null) {
            return;
        }
        this.tagNameView.applyViewModel(valueViewModel);
        this.valueView.applyViewModel(valueViewModel);
    }
}
