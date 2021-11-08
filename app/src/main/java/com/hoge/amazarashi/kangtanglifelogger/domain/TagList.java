package com.hoge.amazarashi.kangtanglifelogger.domain;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplicationComponent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService.ValueRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Inject;

public class TagList {
    @Inject
    TagRepository tagRepository;

    private final List<Tag> list;

    public TagList(KTLLApplicationComponent component) {
        component.inject(this);

        list = new ArrayList<>();
        reload();
    }

    public void reload() {
        list.clear();
        tagRepository.list(list::addAll);
    }

    public void findOrGenerate(final String tagName, Consumer consumer) {
//        Future<?> future = valueRecord.getTagFuture();
//        if (future !=null && !future.isDone()) {
//            future.cancel(true);
//        }

        if (tagName == null || tagName.length() < 1) {
            consumer.accept(null);
            return;
        }
        {
            Tag tag = find(tagName);
            if (tag != null) {
                consumer.accept(tag);
                return;
            }
        }
//        valueRecord.setTagFuture(tagRepository.find(tagName, (tag) -> {
//            if (tag != null) {
//                valueRecord.setTag(tag);
//            } else {
//                valueRecord.setTagFuture(tagRepository.findBySynonymNameOrCreate(tagName, valueRecord::setTag));
//            }
//        }));
        tagRepository.find(tagName, (tag) -> {
            if (tag != null) {
                consumer.accept(tag);
            } else {
                tagRepository.findBySynonymNameOrCreate(tagName, consumer::accept);
            }
        });

    }

    private Tag find(String name) {
        for (Tag tag : list) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        return null;
    }

    public interface Consumer {
        void accept(Tag tag);
    }
}
