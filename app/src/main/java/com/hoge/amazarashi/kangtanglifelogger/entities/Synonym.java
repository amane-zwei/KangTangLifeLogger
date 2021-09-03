package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "synonym")
public class Synonym {
    public Synonym(String name, Tag tag) {
        this.name = name;
        this.tag = tag;
    }

    public Synonym applyId() {
        if (tag != null) {
            tagId = tag.getId();
        }
        return this;
    }

    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private long id;

    @ColumnInfo(name = "tag_id")
    @Getter
    @Setter
    private long tagId;

    @ColumnInfo(name = "value")
    @Getter
    @Setter
    private String name;

    @Ignore
    @JsonIgnore
    @Getter
    private Tag tag;
}
