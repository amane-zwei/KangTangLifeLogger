package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLActionDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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

    public Future<KTLLAction> insert(KTLLAction element) {
        if (element == null || element.getId() != 0) {
            return executorService.submit(() -> element);
        }

        return executorService.submit(() -> {
            element.setId(dao.insert(element));

            List<Value> children = element.getChildren();
            List<Future<Value>> futures = new ArrayList<>(children.size());
            for (Value value : children) {
                futures.add(valueRepository.insert(value));
            }
            for (Future<Value> value : futures) {
                value.get();
            }
            return element;
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
