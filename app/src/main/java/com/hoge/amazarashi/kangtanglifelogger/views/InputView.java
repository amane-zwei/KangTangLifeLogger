package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class InputView extends LinearLayout {

    public InputView(Context context) {
        super(context);

        this.setOrientation(LinearLayout.VERTICAL);

        {
            InputItemView item = new InputItemView(context);
            item.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            this.addView(item);
        }

        {
            Button button = new Button(context);
            button.setText("save");
            this.addView(button);
        }
    }
}
