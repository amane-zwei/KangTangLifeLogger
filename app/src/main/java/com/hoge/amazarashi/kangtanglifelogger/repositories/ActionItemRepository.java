package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.ActionItemDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.ActionItem;

import javax.inject.Inject;

public class ActionItemRepository {

    private final ActionItemDao dao;

    @Inject
    ItemRepository itemRepository;

    public ActionItemRepository(KTLLApplication application, ActionItemDao dao) {
        this.dao = dao;
        application.getApplicationComponent().inject(this);
    }

    public void insert(ActionItem element) {
        itemRepository.insert(
                element.getItem(),
                () -> Executor.IOThread(
                        () -> dao.insert(element.applyId())));
    }
}
