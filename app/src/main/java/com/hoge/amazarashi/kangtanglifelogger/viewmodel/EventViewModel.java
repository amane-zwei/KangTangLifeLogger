package com.hoge.amazarashi.kangtanglifelogger.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class EventViewModel extends ViewModel {

    @Getter
    private final KTLLEvent event;

    @Getter
    private final KTLLAction action;

    @Getter
    private final List<ValueViewModel> values;

    public EventViewModel() {
        this.event = new KTLLEvent();
        this.action = new KTLLAction();
        this.event.add(this.action);

        values = new ArrayList<>();
    }

    public static class ValueViewModel {
        public MutableLiveData<Tag> tag;
        public MutableLiveData<Value> value;

        public ValueViewModel() {
            tag = new MutableLiveData<>();
            value = new MutableLiveData<>();
            tag.setValue(new Tag());
            value.setValue(new Value());
        }
    }
}
