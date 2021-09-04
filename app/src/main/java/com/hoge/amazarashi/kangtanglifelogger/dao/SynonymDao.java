package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hoge.amazarashi.kangtanglifelogger.entities.Synonym;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import java.util.List;

@Dao
public interface SynonymDao {
    @Insert
    long insert(Synonym synonym);

    @Insert
    void insert(List<Synonym> synonyms);

    @Query("select * from synonym where name = :name")
    Synonym find(String name);

    @Query("select * from synonym order by name")
    List<Synonym> list();

    @Query("select * from Synonym")
    List<Synonym> listAll();

    @Query("delete from synonym")
    void deleteAll();

}
