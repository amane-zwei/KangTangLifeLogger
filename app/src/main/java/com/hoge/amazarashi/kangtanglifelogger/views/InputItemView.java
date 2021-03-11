package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoge.amazarashi.kangtanglifelogger.KTLLTheme;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;

public class InputItemView extends LinearLayout {

    private final TagView tagView;
    private final EditText value;

    public InputItemView(Context context) {
        super(context);

        final int paddingH = DisplayMetricsUtil.calcPixel(context, 16);
        final int paddingV = DisplayMetricsUtil.calcPixel(context, 8);

        this.setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(0xffffffff);
        setPadding(paddingH, paddingV, paddingH, paddingV);

        {
            TagView tagView = this.tagView = new TagView(context);
            tagView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.addView(tagView);
        }
        {
            EditText value = this.value = new EditText(context);
            value.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.addView(value);
        }
    }

    private static class TagView extends LinearLayout {
        private final EditText tag;

        public TagView(Context context) {
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
                EditText tag = this.tag = new EditText(context);
                tag.setTextColor(KTLLTheme.textColor);
                tag.setInputType(InputType.TYPE_CLASS_TEXT);
                tag.setMinWidth(tagWidth);
                tag.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                addView(tag);
            }
        }
    }
}
