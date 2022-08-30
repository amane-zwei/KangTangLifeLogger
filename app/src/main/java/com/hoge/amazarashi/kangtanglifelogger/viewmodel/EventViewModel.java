package com.hoge.amazarashi.kangtanglifelogger.viewmodel;

import androidx.lifecycle.ViewModel;

import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.views.ScrollValuesView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class EventViewModel extends ViewModel {

    @Getter
    private final KTLLEvent event;

    @Getter
    private final KTLLAction action;

    private final List<ValueViewModel> values;
    @Getter
    private final ScrollValuesView.Adapter adapter;

    public EventViewModel() {
        this.event = new KTLLEvent();
        this.action = new KTLLAction();
        this.event.add(this.action);

        values = new ArrayList<>();
        adapter = new ScrollValuesView.Adapter();
        adapter.attacheValues(values);
    }

    public ValueViewModel addValue() {
        ValueViewModel valueViewModel = new ValueViewModel();
        values.add(valueViewModel);
        adapter.notifyItemInserted(values.size() - 1);
        return valueViewModel;
    }

    public class ValueViewModel {
        public Tag tag;
        public Value value;

        public ValueViewModel() {
            tag = new Tag();
            value = new Value();
        }

        public void putTag(Tag tag) {
            if (this.tag == tag) {
                return;
            }
            this.tag = tag;
            adapter.notifyItemChanged(values.indexOf(this));
        }

        public void putValue(Value value) {
            if (this.value == null) {
                if (value == null) {
                    return;
                }
            } else {
                if (this.value.equals(value)) {
                    return;
                }
            }
            this.value = value;
            adapter.notifyItemChanged(values.indexOf(this));
        }
    }
}
