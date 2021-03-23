package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "event")
public class KTLLEvent {
    public KTLLEvent(
            String dateFrom,
            String dateTo,
            List<EventTag> children) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.children = children;
        for (EventTag child : children) {
            child.event = this;
        }

//        this.createDate = new Date();
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
    private List<EventTag> children;
}
