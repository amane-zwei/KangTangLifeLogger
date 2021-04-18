package com.hoge.amazarashi.kangtanglifelogger.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hoge.amazarashi.kangtanglifelogger.dao.ActionItemDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.ItemDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLActionDao;
import com.hoge.amazarashi.kangtanglifelogger.dao.KTLLEventDao;
import com.hoge.amazarashi.kangtanglifelogger.entities.ActionItem;
import com.hoge.amazarashi.kangtanglifelogger.entities.Item;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;

@Database(
        entities = {KTLLEvent.class, KTLLAction.class, ActionItem.class, Item.class},
        version = 1, exportSchema = false)
public abstract class KTLLDatabase extends RoomDatabase {
    public abstract KTLLEventDao KTLLEventDao();
    public abstract KTLLActionDao KTLLActionDao();
    public abstract ActionItemDao actionItemDao();
    public abstract ItemDao itemDao();
}
