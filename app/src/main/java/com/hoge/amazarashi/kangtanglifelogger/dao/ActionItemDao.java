package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.hoge.amazarashi.kangtanglifelogger.entities.ActionItem;

@Dao
public interface ActionItemDao {
    @Insert
    long insert(ActionItem actionItem);
}
