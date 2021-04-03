package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.ActionTagDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.ActionTag;

import javax.inject.Inject;

public class ActionTagRepository {

    private final ActionTagDao dao;

    @Inject
    TagRepository tagRepository;

    public ActionTagRepository(KTLLApplication application, ActionTagDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(ActionTag element) {
        tagRepository.insert(
                element.getTag(),
                () -> Executor.IOThread(
                        () -> dao.insert(element.applyId())));
    }
}
