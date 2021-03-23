package com.hoge.amazarashi.kangtanglifelogger.database;

import android.content.Context;

import androidx.room.Room;

import com.hoge.amazarashi.kangtanglifelogger.application.ApplicationContext;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class KTLLDatabaseModule {

    private static final String dbName = "ktll-database";

    @ApplicationContext
    private final Context context;

    public KTLLDatabaseModule(@ApplicationContext Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    @DatabaseContext
    public KTLLDatabase provideDatabase() {
        return Room.databaseBuilder(context, KTLLDatabase.class, dbName)
                .fallbackToDestructiveMigration()
                .build();
    }
}
