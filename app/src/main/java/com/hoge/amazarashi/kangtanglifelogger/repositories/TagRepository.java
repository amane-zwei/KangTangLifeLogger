package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.dao.TagDao;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import javax.inject.Inject;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TagRepository {

    private final TagDao dao;

    public void insert(Tag element) {
        Executor.IOThread(() -> element.setId(dao.insert(element)));
    }
}
