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
@Entity(tableName = "action")
public class KTLLAction {
    @Ignore
    public KTLLAction(
            String actionAt) {
        this.actionAt = actionAt;
        this.children = new ArrayList<>();

//        this.createDate = new Date();
    }

    public void add(Tag tag) {
        if (tag == null) {
            return;
        }
        children.add(new ActionTag(this, tag));
    }

    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private long id;

    @ColumnInfo(name = "action_at")
    @Getter
    @Setter
    private String actionAt;

    @Ignore
    @Getter
    private List<ActionTag> children;
}
