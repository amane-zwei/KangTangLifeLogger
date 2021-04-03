package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "action_tag")
public class ActionTag {
    public ActionTag(KTLLAction action, Tag tag) {
        this.action = action;
        this.tag = tag;
    }

    public ActionTag applyId() {
        actionId = action.getId();
        tagId = tag.getId();
        return this;
    }

    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private long id;

    @ColumnInfo(name = "action_id")
    @Getter
    @Setter
    private long actionId;

    @ColumnInfo(name = "tag_id")
    @Getter
    @Setter
    private long tagId;

    @Ignore
    @Getter
    private KTLLAction action;

    @Ignore
    @Getter
    private Tag tag;
}
