package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoge.amazarashi.kangtanglifelogger.KTLLTheme;
import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.drawables.DividerDrawable;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService.ValueRecord;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;
import com.hoge.amazarashi.kangtanglifelogger.viewmodel.EventViewModel;

import lombok.Getter;

public class InputValueView extends LinearLayout {

    @Getter
    private final TagNameView tagNameView;
    @Getter
    private final ValueView valueView;
    private final Button deleteButton;

    private EventViewModel.ValueViewModel valueViewModel;

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

    public void applyValue(EventViewModel.ValueViewModel valueViewModel) {
        if (valueViewModel == null) {
            return;
        }
        if (valueViewModel.tag != null) {
            this.tagNameView.applyTag(valueViewModel.tag.getValue());
        }
        if (valueViewModel.value != null) {
            this.valueView.applyValue(valueViewModel.value.getValue());
        }
    }
    public InputValueView setValueRecord(EventViewModel.ValueViewModel valueViewModel) {
        this.valueViewModel = valueViewModel;
        return this;
    }

    public class TagNameView extends LinearLayout {
        private final KTLLEditText tagName;
        private String prev = null;

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
                tagName.setOnFocusChangeListener(this::onFocusChange);
                tagName.setMinWidth(tagWidth);
                tagName.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                addView(tagName);
            }
        }

        private void onFocusChange(View view, boolean hasFocus) {
            String tmpName = getName();
            if (!(hasFocus || equals(tmpName))) {
                findOrGenerateTag(tmpName);
            }
        }

        private void findOrGenerateTag(String tagName) {
            ((KTLLApplication) getContext().getApplicationContext())
                    .getTagList()
                    .findOrGenerate(tagName, valueViewModel::putTag);
        }

        public void applyTag(Tag tag) {
            if (tag != null && !equals(tag.getName())) {
                setTagName(tag.getName());
            }
        }

        public void setTagName(String name) {
            tagName.setText(name);
            prev = name;
        }
        public String getName() {
            String name = tagName.getText().toString();
            return name == null ? null : name.trim();
        }

        private boolean equals(String target) {
            if (prev == null) {
                return target == null;
            } else {
                return prev.equals(target);
            }
        }
    }

    public class ValueView extends KTLLEditText {

        private String prev = null;

        public ValueView(Context context) {
            super(context);

            setOnFocusChangeListener(this::onFocusChange);
        }

        private void onFocusChange(View view, boolean hasFocus) {
            String valueString = getValue();

            if (!(hasFocus || equals(valueString))) {
                Value value = new Value();
                value.setInputValue(valueString);
                value.setValue(valueString);
                valueViewModel.putValue(value);
            }
        }

        public void applyValue(Value value) {
            if (value != null) {
                setValue(value.getInputValue());
            }
        }

        public void setValue(String value) {
            setText(value);
            prev = value;
        }

        public String getValue() {
            String value = getText().toString();
            return value == null ? null : value.trim();
        }

        private boolean equals(String value) {
            if (prev == null) {
                return value == null;
            } else {
                return prev.equals(value);
            }
        }
    }
}
