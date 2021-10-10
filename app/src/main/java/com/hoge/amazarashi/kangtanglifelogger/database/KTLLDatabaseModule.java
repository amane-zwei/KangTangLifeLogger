package com.hoge.amazarashi.kangtanglifelogger.database;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class KTLLDatabaseModule {

    private static final String dbName = "ktll-database";
    private static final String initialData = "initial-data/" + dbName + ".db";

    private final Context context;

    public KTLLDatabaseModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public KTLLDatabase provideDatabase() {
        return Room.databaseBuilder(context, KTLLDatabase.class, dbName)
                .createFromAsset(initialData)
                .fallbackToDestructiveMigration()
                .build();
    }
}
