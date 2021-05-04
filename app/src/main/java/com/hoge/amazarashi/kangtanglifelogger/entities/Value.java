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
@Entity(tableName = "value")
public class Value {
    public Value(Tag tag, String inputValue) {
        this.action = null;
        this.tag = tag;
        this.item = null;
        this.inputValue = inputValue;
        this.value = inputValue;
    }

    public Value applyId() {
        actionId = action.getId();
        if (tag != null) {
            tagId = tag.getId();
        }
        if (item != null) {
            itemId = item.getId();
        }
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

    @ColumnInfo(name = "item_id")
    @Getter
    @Setter
    private long itemId;

    @ColumnInfo(name = "input_value")
    @Getter
    @Setter
    private String inputValue;

    @ColumnInfo(name = "value")
    @Getter
    @Setter
    private String value;

    @Ignore
    @JsonIgnore
    @Getter
    @Setter
    private KTLLAction action;

    @Ignore
    @JsonIgnore
    @Getter
    private Tag tag;

    @Ignore
    @JsonIgnore
    @Getter
    private Item item;
}
