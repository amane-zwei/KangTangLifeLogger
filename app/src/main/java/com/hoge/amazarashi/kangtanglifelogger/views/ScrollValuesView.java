package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService.ValueRecord;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ScrollValuesView extends ScrollView {

    private final LinearLayout layout;
    @Getter
    private final List<ValueRecord> values;

    public ScrollValuesView(Context context) {
        super(context);

        {
            LinearLayout layout = this.layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(0, 0, 0, 80);
            addView(layout);
        }
        values = new ArrayList<>();
    }

    public InputValueView add() {
        return add(new ValueRecord());
    }

    public InputValueView add(ValueRecord valueRecord) {
        values.add(valueRecord);

        final InputValueView inputValueView = new InputValueView(getContext());
        inputValueView.setValueRecord(valueRecord);
        inputValueView.setOnClickDelete((view) -> {
            values.remove(valueRecord);
            layout.removeView(inputValueView);
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 0);
        inputValueView.setLayoutParams(layoutParams);
        layout.addView(inputValueView);
        invalidate();

        return inputValueView;
    }

    @androidx.annotation.RequiresPermission(anyOf = {
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"})
    public InputValueView addLocation() {
        ValueRecord locationValueRecord = new ValueRecord();
        InputValueView result = add(locationValueRecord);

        ((KTLLApplication) getContext().getApplicationContext())
                .getTagList()
                .findOrGenerate("location", locationValueRecord, () -> {});

        ((KTLLApplication) getContext().getApplicationContext())
                .getLocationClient()
                .getLastLocation(getContext(), location -> {
                    locationValueRecord.getValue().setValue(location.toString());
                    result.applyValueRecord();
                });

        return result;
    }
}
