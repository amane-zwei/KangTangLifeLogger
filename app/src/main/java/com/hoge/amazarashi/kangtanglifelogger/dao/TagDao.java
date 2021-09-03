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

    @Insert
    void insert(List<Tag> tags);

    @Query("select * from tag where name = :name")
    Tag find(String name);

    @Query("select * from tag order by name")
    List<Tag> list();

    @Query("select * from tag")
    List<Tag> listAll();

    @Query("delete from tag")
    void deleteAll();

}
