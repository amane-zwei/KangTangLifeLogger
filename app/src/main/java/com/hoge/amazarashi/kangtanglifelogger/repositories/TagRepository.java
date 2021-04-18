package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.TagDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

public class TagRepository {

    private final TagDao dao;

    public TagRepository(KTLLApplication application, TagDao dao) {
        this.dao = dao;
    }

    public void insert(Tag element, Runnable runnable) {
        if (element == null || element.getId() != 0) {
            runnable.run();
            return;
        }
        Executor.IOThread(() -> {
            element.setId(dao.insert(element));
            runnable.run();
        });
    }
}
