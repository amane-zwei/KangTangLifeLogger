package com.hoge.amazarashi.kangtanglifelogger.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
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
public class Value extends BaseObservable {

    public Value applyId() {
        actionId = action.getId();
        if (tag != null) {
            tagId = tag.getId();
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

    @Bindable
    @ColumnInfo(name = "input_value")
    @Getter
    private String inputValue;

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
        this.value = inputValue;
        notifyPropertyChanged(BR.inputValue);
    }

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
    @Setter
    private Tag tag;

    public boolean equals(Value value) {
        return value != null && this.inputValue != null && this.inputValue.equals(value.inputValue);
    }
}
