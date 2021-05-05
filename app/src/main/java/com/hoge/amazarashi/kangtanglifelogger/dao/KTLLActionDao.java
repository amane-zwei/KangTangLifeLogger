package com.hoge.amazarashi.kangtanglifelogger.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import java.util.List;

@Dao
public interface KTLLActionDao {
//        @Query("SELECT * FROM account_data WHERE date > :startDate AND date < :lastDate ORDER BY date ASC")
//        List<AccountData> getData(long startDate, long lastDate);

    @Insert
    long insert(KTLLAction action);

    @Insert
    void insert(List<KTLLAction> actions);

    @Update
    void update(KTLLAction action);

    @Delete
    void delete(KTLLAction action);

    @Query("select * from `action`")
    List<KTLLAction> listAll();

    @Query("delete from `action`")
    void deleteAll();
}
