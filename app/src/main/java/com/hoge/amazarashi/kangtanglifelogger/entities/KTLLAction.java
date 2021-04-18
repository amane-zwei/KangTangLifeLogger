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

    public void add(Item item) {
        if (item == null) {
            return;
        }
        children.add(item);
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
    private List<Item> children;
}
