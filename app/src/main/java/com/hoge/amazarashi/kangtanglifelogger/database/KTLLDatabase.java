package com.hoge.amazarashi.kangtanglifelogger.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hoge.amazarashi.kangtanglifelogger.dao.ActionTagDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLActionDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.TagDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.ActionTag;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;

@Database(
        entities = {KTLLEvent.class, KTLLAction.class, ActionTag.class, Tag.class},
        version = 1, exportSchema = false)
public abstract class KTLLDatabase extends RoomDatabase {
    public abstract KTLLEventDao KTLLEventDao();
    public abstract KTLLActionDao KTLLActionDao();
    public abstract ActionTagDao actionTagDao();
    public abstract TagDao tagDao();
}
