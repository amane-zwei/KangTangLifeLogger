package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoge.amazarashi.kangtanglifelogger.KTLLTheme;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;

public class InputValueView extends LinearLayout {

    private final TagNameView tagNameView;
    private final EditText valueView;

    public InputValueView(Context context) {
        super(context);

        final int paddingH = DisplayMetricsUtil.calcPixel(context, 16);
        final int paddingV = DisplayMetricsUtil.calcPixel(context, 8);

        this.setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(0xffffffff);
        setPadding(paddingH, paddingV, paddingH, paddingV);

        {
            TagNameView tagNameView = this.tagNameView = new TagNameView(context);
            tagNameView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.addView(tagNameView);
        }
        {
            EditText valueView = this.valueView = new EditText(context);
            valueView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.addView(valueView);
        }
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
        return new Tag(tagName);
    }

    private static class TagNameView extends LinearLayout {
        private final EditText tagName;

        public TagNameView(Context context) {
            super(context);

            final int tagWidth = DisplayMetricsUtil.calcWidth(context, 30);

            {
                TextView label = new TextView(context);
                label.setText("#");
                label.setTextColor(KTLLTheme.textColor);
                label.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                addView(label);
            }

            {
                EditText tagName = this.tagName = new EditText(context);
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
