package com.hoge.amazarashi.kangtanglifelogger.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hoge.amazarashi.kangtanglifelogger.dao.EventTagDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.TagDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.EventTag;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

import javax.inject.Inject;

@Database(
        entities = {KTLLEvent.class, EventTag.class, Tag.class},
        version = 1, exportSchema = false)
public abstract class KTLLDatabase extends RoomDatabase {
    public abstract KTLLEventDao KTLLEventDao();
    public abstract EventTagDao eventTagDao();
    public abstract TagDao tagDao();
}
