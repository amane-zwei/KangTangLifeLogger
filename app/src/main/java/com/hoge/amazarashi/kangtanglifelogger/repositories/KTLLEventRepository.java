package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;

import javax.inject.Inject;

public class KTLLEventRepository {

    KTLLDatabase database;

    private final KTLLEventDao dao;

    public KTLLEventRepository() {
        dao = database.KTLLEventDao();
    }

    public void insert(KTLLEvent element) {
        Executor.IOThread(() -> element.setId(dao.insert(element)));
    }
}
