package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "event")
public class KTLLEvent {
    @Ignore
    public KTLLEvent(
            String dateFrom,
            String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.children = new ArrayList<>();

//        this.createDate = new Date();
    }

    public void add(Tag tag) {
        if (tag == null) {
            return;
        }
        children.add(new EventTag(this, tag));
    }

    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private long id;

    @ColumnInfo(name = "date_from")
    @Getter
    @Setter
    private String dateFrom;
    @ColumnInfo(name = "date_to")
    @Getter
    @Setter
    private String dateTo;

//    @ColumnInfo(name = "create_date")
//    private Date createDate;

    @Ignore
    @Getter
    private List<EventTag> children;
}
