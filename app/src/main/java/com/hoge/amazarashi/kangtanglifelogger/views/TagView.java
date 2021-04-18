package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

import java.util.ArrayList;
import java.util.List;

public class TagView extends ScrollView {

    private final LinearLayout layout;
    private final List<InputItemView> items;

    public TagView(Context context) {
        super(context);

        addView(layout = new LinearLayout(context));
        items = new ArrayList<>();
    }

    public void add() {
        InputItemView inputItemView = new InputItemView(getContext());
        items.add(inputItemView);
        layout.addView(inputItemView);
    }

    public List<Value> generate() {
        List<Value> result = new ArrayList<>();
        for(InputItemView inputItemView : items) {
            result.add(inputItemView.generateValue());
        }
        return result;
    }
}
