package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.View;

import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.viewmodel.EventViewModel;

public class ValueView extends KTLLEditText {

    private EventViewModel.ValueViewModel valueViewModel;
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
            this.post(()->{
                valueViewModel.putValue(value);
            });
        }
    }

    public void applyViewModel(EventViewModel.ValueViewModel valueViewModel) {
        this.valueViewModel = valueViewModel;
        applyValue(valueViewModel.value);
    }

    private void applyValue(Value value) {
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
