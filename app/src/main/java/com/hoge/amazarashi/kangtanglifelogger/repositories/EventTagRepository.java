package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.dao.EventTagDao;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.entities.EventTag;

import javax.inject.Inject;

public class EventTagRepository {

    KTLLDatabase database;

    private final EventTagDao dao;

    public EventTagRepository() {
        dao = database.eventTagDao();
    }

    public void insert(EventTag element) {
        Executor.IOThread(() -> dao.insert(element));
    }
}
