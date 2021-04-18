package com.hoge.amazarashi.kangtanglifelogger.application;

import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabaseModule;
import com.hoge.amazarashi.kangtanglifelogger.fragments.InputFragment;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.ValueRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLActionRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.ItemRepository;
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
    void inject(KTLLActionRepository repository);
    void inject(ValueRepository repository);
    void inject(TagRepository repository);
    void inject(ItemRepository repository);
}