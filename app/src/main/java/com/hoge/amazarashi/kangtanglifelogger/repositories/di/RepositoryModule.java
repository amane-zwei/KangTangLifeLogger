package com.hoge.amazarashi.kangtanglifelogger.repositories.di;

import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public TagRepository provideTagRepository(KTLLDatabase database) {
        return new TagRepository(database.tagDao());
    }
}
