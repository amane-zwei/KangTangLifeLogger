package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "event_tag")
public class EventTag {
    public EventTag(Tag tag) {
        this.event = null;
        this.tag = tag;

//        this.createDate = new Date();
    }

    public EventTag applyId() {
        eventId = event.getId();
        tagId = tag.getId();
        return this;
    }

    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private long id;

    @ColumnInfo(name = "event_id")
    @Getter
    @Setter
    private long eventId;

    @ColumnInfo(name = "tag_id")
    @Getter
    @Setter
    private long tagId;

//    @ColumnInfo(name = "create_date")
//    private Date createDate;

    @Ignore
    KTLLEvent event;

    @Ignore
    private Tag tag;
}
