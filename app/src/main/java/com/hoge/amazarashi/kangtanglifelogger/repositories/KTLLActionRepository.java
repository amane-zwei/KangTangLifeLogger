package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLActionDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.ActionItem;
import com.hoge.amazarashi.kangtanglifelogger.entities.Item;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;

import javax.inject.Inject;

public class KTLLActionRepository {

    private final KTLLActionDao dao;

    @Inject
    ActionItemRepository actionItemRepository;

    public KTLLActionRepository(KTLLApplication application, KTLLActionDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(KTLLAction element) {
        Executor.IOThread(() -> {
            element.setId(dao.insert(element));

            for (Item item : element.getChildren()) {
                actionItemRepository.insert(new ActionItem(element, item));
            }
        });
    }
}
