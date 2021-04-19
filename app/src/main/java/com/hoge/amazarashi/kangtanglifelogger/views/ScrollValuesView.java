package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

import java.util.ArrayList;
import java.util.List;

public class ScrollValuesView extends ScrollView {

    private final LinearLayout layout;
    private final List<InputValueView> items;

    public ScrollValuesView(Context context) {
        super(context);

        addView(layout = new LinearLayout(context));
        items = new ArrayList<>();
    }

    public void add() {
        InputValueView inputValueView = new InputValueView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        inputValueView.setLayoutParams(layoutParams);
        items.add(inputValueView);
        layout.addView(inputValueView);
    }

    public List<Value> generateValues() {
        List<Value> result = new ArrayList<>();
        for(InputValueView inputValueView : items) {
            result.add(inputValueView.generateValue());
        }
        return result;
    }
}