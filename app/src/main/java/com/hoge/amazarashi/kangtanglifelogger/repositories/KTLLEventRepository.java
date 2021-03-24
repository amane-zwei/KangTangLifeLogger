package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.entities.EventTag;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import javax.inject.Inject;

import lombok.AllArgsConstructor;

public class KTLLEventRepository {

    private final KTLLEventDao dao;

    @Inject
    EventTagRepository eventTagRepository;

    public KTLLEventRepository(KTLLApplication application, KTLLEventDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(KTLLEvent element) {
        Executor.IOThread(() -> {
            element.setId(dao.insert(element));

            for (EventTag eventTag : element.getChildren()) {
                eventTagRepository.insert(eventTag);
            }
        });
    }
}
