package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

@Dao
public interface ValueDao {
    @Insert
    long insert(Value value);
}
