package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import androidx.databinding.library.baseAdapters.BR;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "tag", indices = {@Index(value = "name", unique = true)})
public class Tag extends BaseObservable {

    @Ignore
    public Tag(
            String name) {
        this.name = name;
    }

    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private long id;

    @Bindable
    @ColumnInfo(name = "name")
    @Getter
    private String name;

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
