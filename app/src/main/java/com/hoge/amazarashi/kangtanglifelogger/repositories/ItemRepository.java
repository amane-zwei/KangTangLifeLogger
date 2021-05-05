package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.ItemDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.Item;

import java.util.List;

public class ItemRepository {

    private final ItemDao dao;

    public ItemRepository(KTLLApplication application, ItemDao dao) {
        this.dao = dao;
    }

    public void insert(Item element, Runnable runnable) {
        if (element == null || element.getId() != 0) {
            runnable.run();
            return;
        }
        Executor.IOThread(() -> {
            element.setId(dao.insert(element));
            runnable.run();
        });
    }

    public List<Item> listAll() {
        return dao.listAll();
    }

    public void replace(List<Item> items) {
        dao.deleteAll();
        dao.insert(items);
    }
}
