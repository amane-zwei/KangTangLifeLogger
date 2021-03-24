package com.hoge.amazarashi.kangtanglifelogger.application;

import android.app.Application;

import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabaseModule;
import com.hoge.amazarashi.kangtanglifelogger.repositories.di.RepositoryModule;

import lombok.Getter;

public class KTLLApplication extends Application {

    @Getter
    private KTLLApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerKTLLApplicationComponent
                .builder()
                .kTLLDatabaseModule(new KTLLDatabaseModule(this))
                .repositoryModule(new RepositoryModule(this))
                .build();
    }
}
