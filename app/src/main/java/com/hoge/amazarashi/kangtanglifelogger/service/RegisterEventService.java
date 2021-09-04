package com.hoge.amazarashi.kangtanglifelogger.service;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class RegisterEventService {

    private final ExecutorService executorService;

    @Inject
    KTLLEventRepository eventRepository;

    public RegisterEventService(KTLLApplication application) {
        this.executorService = application.getExecutorService();
        application.getApplicationComponent().inject(this);
    }

    public void register(final EventRecord event) {
        executorService.submit(() -> {
            for (ValueRecord record : event.values) {
                try {
                    if (record.tagFuture != null) {
                        record.tag = record.tagFuture.get();
                    }
                } catch (ExecutionException | InterruptedException ignored) {
                }
            }
            eventRepository.insert(buildEvent(event));
        });
    }

    private static KTLLEvent buildEvent(EventRecord src) {
        KTLLEvent result = src.event;
        src.action.reAdd(buildValues(src.action, src.values));
        result.reAdd(src.action);
        return result;
    }

    private static List<Value> buildValues(KTLLAction action, List<ValueRecord> src) {
        List<Value> result = new ArrayList<>();
        for (ValueRecord record : src) {
            Value value = record.value;
            value.setAction(action);
            value.setTag(record.tag);
            result.add(value);
        }
        return result;
    }

    @AllArgsConstructor
    public static class EventRecord {

        private final KTLLEvent event;

        private final KTLLAction action;

        @Getter
        private final List<ValueRecord> values;
    }

    public static class ValueRecord {
        public ValueRecord() {
            value = new Value();
        }

        @Getter
        @Setter
        private Tag tag;
        @Getter
        @Setter
        private Value value;
        @Getter
        @Setter
        private Future<Tag> tagFuture;
    }
}