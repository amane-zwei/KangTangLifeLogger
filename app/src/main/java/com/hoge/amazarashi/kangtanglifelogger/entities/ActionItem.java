package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "action_item")
public class ActionItem {
    public ActionItem(KTLLAction action, Item item) {
        this.action = action;
        this.item = item;
    }

    public ActionItem applyId() {
        actionId = action.getId();
        itemId = item.getId();
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

    @ColumnInfo(name = "item_id")
    @Getter
    @Setter
    private long itemId;

    @Ignore
    @Getter
    private KTLLAction action;

    @Ignore
    @Getter
    private Item item;
}
