package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;

@Dao
public interface KTLLEventDao {
//        @Query("SELECT * FROM account_data WHERE date > :startDate AND date < :lastDate ORDER BY date ASC")
//        List<AccountData> getData(long startDate, long lastDate);

    @Insert
    long insert(KTLLEvent event);

    @Update
    void update(KTLLEvent event);

    @Delete
    void delete(KTLLEvent event);
}
