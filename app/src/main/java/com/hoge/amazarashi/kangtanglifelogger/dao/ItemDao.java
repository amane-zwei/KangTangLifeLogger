package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.hoge.amazarashi.kangtanglifelogger.entities.Item;

@Dao
public interface ItemDao {
    @Insert
    long insert(Item item);
}
