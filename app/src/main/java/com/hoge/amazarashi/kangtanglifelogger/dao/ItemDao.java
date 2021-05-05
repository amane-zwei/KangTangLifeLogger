package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hoge.amazarashi.kangtanglifelogger.entities.Item;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    long insert(Item item);

    @Insert
    long insert(List<Item> items);

    @Query("select * from item")
    List<Item> listAll();

    @Query("delete from item")
    void deleteAll();
}
