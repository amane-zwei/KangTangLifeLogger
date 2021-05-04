package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "event")
public class KTLLEvent {

    public KTLLEvent() {
        this.children = new ArrayList<>();
    }

    public void add(KTLLAction action) {
        if (action == null) {
            return;
        }
        children.add(action);
    }

    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private long id;

    @Ignore
    @JsonIgnore
    @Getter
    private final List<KTLLAction> children;
}
