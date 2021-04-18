package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;

import javax.inject.Inject;

public class KTLLEventRepository {

    private final KTLLEventDao dao;

    @Inject
    KTLLActionRepository actionRepository;

    public KTLLEventRepository(KTLLApplication application, KTLLEventDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(KTLLEvent element) {
        Executor.IOThread(() -> {
            element.setId(dao.insert(element));

            for (KTLLAction action : element.getChildren()) {
                actionRepository.insert(action);
            }
        });
    }
}
