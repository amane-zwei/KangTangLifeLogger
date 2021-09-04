package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLActionDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;

import java.util.List;

import javax.inject.Inject;

public class KTLLActionRepository {

    private final KTLLActionDao dao;

    @Inject
    ValueRepository valueRepository;

    public KTLLActionRepository(KTLLApplication application, KTLLActionDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(KTLLAction element) {
        Executor.IOThread(() -> {
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
