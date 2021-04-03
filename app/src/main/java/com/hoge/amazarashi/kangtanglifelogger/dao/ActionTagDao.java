package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.hoge.amazarashi.kangtanglifelogger.entities.ActionTag;

@Dao
public interface ActionTagDao {
    @Insert
    long insert(ActionTag actionTag);
}
