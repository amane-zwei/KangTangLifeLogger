package com.hoge.amazarashi.kangtanglifelogger.repositories;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.dao.TagDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TagRepository {

    private final TagDao dao;

    ExecutorService executorService;

    public TagRepository(KTLLApplication application, TagDao dao) {
        this.dao = dao;
        this.executorService = application.getExecutorService();
    }

    public void insert(Tag element, Runnable runnable) {
        if (element == null || element.getId() != 0) {
            runnable.run();
            return;
        }
        executorService.submit(() -> {
            element.setId(dao.insert(element));
            runnable.run();
        });
    }

    public Future<Tag> find(String tagName, TagListener listener) {
        return executorService.submit(() -> {
            Tag tag = dao.find(tagName);
            listener.onLoaded(tag);
            return tag;
        });
    }

    public Future<Tag> findBySynonymNameOrCreate(String name, TagListener listener) {
        return executorService.submit(() -> {
            Tag tag = dao.findBySynonymName(name);
            if (tag == null) {
                tag = new Tag(name);
            }
            listener.onLoaded(tag);
            return tag;
        });
    }

    public void list(TagListListener listener) {
        executorService.submit(() -> listener.onLoaded(dao.list()));
    }

    public List<Tag> listAll() {
        return dao.listAll();
    }

    public void replace(List<Tag> tags) {
        dao.deleteAll();
        dao.insert(tags);
    }

    public interface TagListener {
        void onLoaded(Tag tag);
    }

    public interface TagListListener {
        void onLoaded(List<Tag> list);
    }
}
