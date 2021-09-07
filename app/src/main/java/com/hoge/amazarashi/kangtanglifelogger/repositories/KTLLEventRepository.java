package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;

public class KTLLEventRepository {

    private final KTLLEventDao dao;

    private final ExecutorService executorService;

    @Inject
    KTLLActionRepository actionRepository;

    public KTLLEventRepository(KTLLApplication application, KTLLEventDao dao) {
        this.dao = dao;
        this.executorService = application.getExecutorService();
        application.getApplicationComponent().inject(this);
    }

    public Future<KTLLEvent> insert(KTLLEvent element) {
        if (element == null || element.getId() != 0) {
            return executorService.submit(() -> element);
        }

        return executorService.submit(() -> {
            element.setId(dao.insert(element));

            List<KTLLAction> children = element.getChildren();
            List<Future<KTLLAction>> futures = new ArrayList<>(children.size());
            for (KTLLAction action : children) {
                futures.add(actionRepository.insert(action));
            }
            for (Future<KTLLAction> action : futures) {
                action.get();
            }
            return element;
        });
    }

    public List<KTLLEvent> listAll() {
        return dao.listAll();
    }

    public void replace(List<KTLLEvent> events) {
        dao.deleteAll();
        dao.insert(events);
    }
}
