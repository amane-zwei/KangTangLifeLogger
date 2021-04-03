package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLActionDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.ActionTag;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;

import javax.inject.Inject;

public class KTLLActionRepository {

    private final KTLLActionDao dao;

    @Inject
    ActionTagRepository actionTagRepository;

    public KTLLActionRepository(KTLLApplication application, KTLLActionDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(KTLLAction element) {
        Executor.IOThread(() -> {
            element.setId(dao.insert(element));

            for (ActionTag actionTag : element.getChildren()) {
                actionTagRepository.insert(actionTag);
            }
        });
    }
}
