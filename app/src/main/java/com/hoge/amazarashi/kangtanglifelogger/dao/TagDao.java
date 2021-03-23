package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

@Dao
public interface TagDao {
    @Insert
    long insert(Tag tag);
}
