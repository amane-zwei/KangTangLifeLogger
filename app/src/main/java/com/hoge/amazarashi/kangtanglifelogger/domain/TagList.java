package com.hoge.amazarashi.kangtanglifelogger.domain;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplicationComponent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;

import java.util.ArrayList;
import java.util.List;

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

    public Tag findOrGenerate(String name) {
        for (Tag tag : list) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        Tag tag = new Tag(name);
        list.add(tag);
        return tag;
    }
}
