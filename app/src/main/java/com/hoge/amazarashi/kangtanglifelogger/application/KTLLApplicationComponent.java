package com.hoge.amazarashi.kangtanglifelogger.application;

import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabaseModule;
import com.hoge.amazarashi.kangtanglifelogger.fragments.InputFragment;
import com.hoge.amazarashi.kangtanglifelogger.repositories.EventTagRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.di.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        KTLLDatabaseModule.class,
        RepositoryModule.class})
public interface KTLLApplicationComponent {
    void inject(InputFragment inputFragment);

    void inject(KTLLEventRepository repository);
    void inject(EventTagRepository repository);
}