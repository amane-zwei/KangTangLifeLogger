package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.ValueDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

import java.util.List;

import javax.inject.Inject;

public class ValueRepository {

    private final ValueDao dao;

    @Inject
    TagRepository tagRepository;

    public ValueRepository(KTLLApplication application, ValueDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(Value element) {
        tagRepository.insert(
                element.getTag(),
                () -> Executor.IOThread(
                        () -> dao.insert(element.applyId())));
    }

    public List<Value> listAll() {
        return dao.listAll();
    }

    public void replace(List<Value> values) {
        dao.deleteAll();
        dao.insert(values);
    }
}
