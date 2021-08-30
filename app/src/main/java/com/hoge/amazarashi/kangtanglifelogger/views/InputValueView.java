package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoge.amazarashi.kangtanglifelogger.KTLLTheme;
import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.drawables.DividerDrawable;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;

public class InputValueView extends LinearLayout {

    private final TagNameView tagNameView;
    private final KTLLEditText valueView;
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
            KTLLEditText valueView = this.valueView = new KTLLEditText(context);
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

    public Value generateValue() {
        String value = valueView.getText().toString();
        if (value == null) {
            return null;
        }
        return new Value(
                generateTag(),
                value);
    }

    private Tag generateTag() {
        String tagName = tagNameView.getName();
        if (tagName == null) {
            return null;
        }
        tagName = tagName.trim();
        if (tagName.length() < 1) {
            return null;
        }
        return ((KTLLApplication) getContext().getApplicationContext())
                .getTagList()
                .findOrGenerate(tagName);
    }

    private static class TagNameView extends LinearLayout {
        private final KTLLEditText tagName;

        public TagNameView(Context context) {
            super(context);

            final int tagWidth = DisplayMetricsUtil.calcWidth(context, 30);

            {
                TextView label = new TextView(context);
                label.setText("# ");
                label.setTextColor(KTLLTheme.textColor);
                label.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                addView(label);
            }

            {
                KTLLEditText tagName = this.tagName = new KTLLEditText(context);
                tagName.setTextColor(KTLLTheme.textColor);
                tagName.setInputType(InputType.TYPE_CLASS_TEXT);
                tagName.setMinWidth(tagWidth);
                tagName.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                addView(tagName);
            }
        }

        public String getName() {
            return tagName.getText().toString();
        }
    }
}
