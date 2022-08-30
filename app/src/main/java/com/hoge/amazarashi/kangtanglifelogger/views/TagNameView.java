package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoge.amazarashi.kangtanglifelogger.KTLLTheme;
import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.util.DisplayMetricsUtil;
import com.hoge.amazarashi.kangtanglifelogger.viewmodel.EventViewModel;

public class TagNameView extends LinearLayout {
    private final KTLLEditText tagName;
    private EventViewModel.ValueViewModel valueViewModel;
    private String prev = null;

    public TagNameView(Context context) {
        super(context);

        final int tagWidth = DisplayMetricsUtil.calcWidth(context, 30);

        {
            TextView label = new TextView(context);
            label.setText("# ");
            label.setTextColor(KTLLTheme.textColor);
            label.setLayoutParams(new LayoutParams(
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
            tagName.setLayoutParams(new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            addView(tagName);
        }
    }

    private void onFocusChange(View view, boolean hasFocus) {
        String tmpName = getName();
        if (!(hasFocus || equals(tmpName))) {
            prev = tmpName;
            findOrGenerateTag(tmpName);
        }
    }

    private void findOrGenerateTag(String tagName) {
        ((KTLLApplication) getContext().getApplicationContext())
                .getTagList()
                .findOrGenerate(tagName, valueViewModel::putTag);
    }

    public void applyViewModel(EventViewModel.ValueViewModel valueViewModel) {
        this.valueViewModel = valueViewModel;
        applyTag(valueViewModel.tag);
    }

    private void applyTag(Tag tag) {
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
        return name.trim();
    }

    private boolean equals(String target) {
        if (prev == null) {
            return target == null;
        } else {
            return prev.equals(target);
        }
    }
}
