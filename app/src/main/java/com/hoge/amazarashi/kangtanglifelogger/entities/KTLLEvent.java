package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "event")
public class KTLLEvent {
    @Ignore
    public KTLLEvent(
            String title) {
        this.title = title;
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

    @ColumnInfo(name = "title")
    @Getter
    @Setter
    private String title;

    @Ignore
    @Getter
    private List<KTLLAction> children;
}
