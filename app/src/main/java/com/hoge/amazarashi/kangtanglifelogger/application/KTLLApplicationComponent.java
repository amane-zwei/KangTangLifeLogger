package com.hoge.amazarashi.kangtanglifelogger.application;

import android.content.Context;

import com.hoge.amazarashi.kangtanglifelogger.database.DatabaseContext;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabaseModule;
import com.hoge.amazarashi.kangtanglifelogger.fragments.InputFragment;
import com.hoge.amazarashi.kangtanglifelogger.repositories.di.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        KTLLApplicationModule.class,
        KTLLDatabaseModule.class,
        RepositoryModule.class})
public interface KTLLApplicationComponent {
    void inject(InputFragment inputFragment);
}