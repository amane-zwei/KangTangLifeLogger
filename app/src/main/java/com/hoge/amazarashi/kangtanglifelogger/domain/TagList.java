package com.hoge.amazarashi.kangtanglifelogger.domain;

import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagList {
    private final List<Tag> list;

    public TagList() {
        list = new ArrayList<>();
    }

    public void put(List<Tag> list) {
        this.list.addAll(list);
    }

    public Tag get(String name) {
        for (Tag tag : list) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        return null;
    }
}
