package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLActionDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class KTLLActionRepository {

    private final KTLLActionDao dao;

    private final ExecutorService executorService;

    @Inject
    ValueRepository valueRepository;

    public KTLLActionRepository(KTLLApplication application, KTLLActionDao dao) {
        this.dao = dao;
        this.executorService = application.getExecutorService();
        application.getApplicationComponent().inject(this);
    }

    public void insert(KTLLAction element) {
        executorService.submit(() -> {
            element.setId(dao.insert(element));

            for (Value value : element.getChildren()) {
                valueRepository.insert(value);
            }
        });
    }

    public List<KTLLAction> listAll() {
        return dao.listAll();
    }

    public void replace(List<KTLLAction> actions) {
        dao.deleteAll();
        dao.insert(actions);
    }
}
