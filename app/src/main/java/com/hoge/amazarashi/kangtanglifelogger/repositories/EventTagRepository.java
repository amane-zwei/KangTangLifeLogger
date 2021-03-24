package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.EventTagDao;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.entities.EventTag;

import javax.inject.Inject;

import lombok.AllArgsConstructor;

public class EventTagRepository {

    private final EventTagDao dao;

    @Inject
    TagRepository tagRepository;

    public EventTagRepository(KTLLApplication application, EventTagDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(EventTag element) {
        tagRepository.insert(
                element.getTag(),
                () -> Executor.IOThread(
                        () -> dao.insert(element.applyId())));
    }
}
