package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.SynonymDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.Synonym;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class SynonymRepository {

    private final SynonymDao dao;

    final ExecutorService executorService;

    public SynonymRepository(KTLLApplication application, SynonymDao dao) {
        this.dao = dao;
        this.executorService = application.getExecutorService();
    }

    public void insert(Synonym element, Runnable runnable) {
        if (element == null || element.getId() != 0) {
            runnable.run();
            return;
        }
        executorService.submit(() -> {
            element.setId(dao.insert(element));
            runnable.run();
        });
    }

    public void list(SynonymListListener listener) {
        executorService.submit(() -> listener.onLoaded(dao.list()));
    }

    public List<Synonym> listAll() {
        return dao.listAll();
    }

    public void replace(List<Synonym> synonyms) {
        dao.deleteAll();
        dao.insert(synonyms);
    }

    public interface SynonymListener {
        void onLoaded(Synonym synonym);
    }

    public interface SynonymListListener {
        void onLoaded(List<Synonym> list);
    }
}
