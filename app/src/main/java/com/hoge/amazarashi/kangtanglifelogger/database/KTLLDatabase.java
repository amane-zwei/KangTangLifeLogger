package com.hoge.amazarashi.kangtanglifelogger.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLActionDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.SynonymDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.TagDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.ValueDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Synonym;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;

@Database(
        entities = {KTLLEvent.class, KTLLAction.class, Value.class, Tag.class, Synonym.class},
        version = 1, exportSchema = false)
public abstract class KTLLDatabase extends RoomDatabase {
    public abstract KTLLEventDao KTLLEventDao();
    public abstract KTLLActionDao KTLLActionDao();
    public abstract ValueDao valueDao();
    public abstract TagDao tagDao();
    public abstract SynonymDao SynonymDao();
}
