package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

import java.util.List;

@Dao
public interface ValueDao {
    @Insert
    long insert(Value value);

    @Insert
    long insert(List<Value> values);

    @Query("select * from value")
    List<Value> listAll();

    @Query("delete from value")
    void deleteAll();
}
