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

    public void findOrGenerate(final String tagName, final ValueRecord valueRecord, final Runnable callback) {
        Future<?> future = valueRecord.getTagFuture();
        if (future !=null && !future.isDone()) {
            future.cancel(true);
        }

        if (tagName == null || tagName.length() < 1) {
            valueRecord.setTag(null);
            return;
        }
        {
            Tag tag = find(tagName);
            if (tag != null) {
                valueRecord.setTag(tag);
                return;
            }
        }
        valueRecord.setTagFuture(tagRepository.findOrCreate(tagName, (tag) -> {
            if (tag == null) {
                valueRecord.setTag(null);
            } else {
                valueRecord.setTag(tag);
            }
        }));
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
