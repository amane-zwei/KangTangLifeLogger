package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "item")
public class Item {
    public Item(
            String inputValue) {
        this.inputValue = inputValue;
        this.value = inputValue;

//        this.createDate = new Date();
//        this.updateDate = null;
    }

    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private long id;

    @ColumnInfo(name = "input_value")
    @Getter
    @Setter
    private String inputValue;

    @ColumnInfo(name = "value")
    @Getter
    @Setter
    private String value;

//    @ColumnInfo(name = "create_date")
//    private Date createDate;
//
//    @ColumnInfo(name = "update_date")
//    private Date updateDate;
}
