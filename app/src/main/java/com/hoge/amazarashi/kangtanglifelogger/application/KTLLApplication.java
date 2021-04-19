package com.hoge.amazarashi.kangtanglifelogger.application;

import android.app.Application;

import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabaseModule;
import com.hoge.amazarashi.kangtanglifelogger.domain.TagList;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.di.RepositoryModule;

import javax.inject.Inject;

import lombok.Getter;

public class KTLLApplication extends Application {

    @Getter
    private KTLLApplicationComponent applicationComponent;

    @Getter
    private TagList tagList;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerKTLLApplicationComponent
                .builder()
                .kTLLDatabaseModule(new KTLLDatabaseModule(this))
                .repositoryModule(new RepositoryModule(this))
                .build();

        tagList = new TagList(applicationComponent);
    }
}
