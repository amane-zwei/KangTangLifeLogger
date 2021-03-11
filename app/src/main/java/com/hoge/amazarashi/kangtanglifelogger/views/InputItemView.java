package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

public class InputItemView extends LinearLayout {

    private EditText inputTag;
    private EditText inputValue;

    public InputItemView(Context context) {
        super(context);

        this.setOrientation(LinearLayout.VERTICAL);

        {
            inputTag = new EditText(context);
            this.addView(inputTag);
        }
        {
            inputValue = new EditText(context);
            this.addView(inputValue);
        }
    }
}
