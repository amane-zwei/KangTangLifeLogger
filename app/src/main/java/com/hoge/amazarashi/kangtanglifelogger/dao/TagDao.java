package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import java.util.List;

@Dao
public interface TagDao {
    @Insert
    long insert(Tag tag);

    @Query("select * from tag order by name")
    List<Tag> list();
}
