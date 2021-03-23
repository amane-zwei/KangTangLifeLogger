package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.hoge.amazarashi.kangtanglifelogger.entities.EventTag;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;

@Dao
public interface EventTagDao {
    @Insert
    long insert(EventTag eventTag);
}
