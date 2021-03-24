package com.hoge.amazarashi.kangtanglifelogger.repositories.di;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.repositories.EventTagRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;

@Module
@AllArgsConstructor
public class RepositoryModule {

    private final KTLLApplication application;

    @Singleton
    @Provides
    public KTLLEventRepository provideKTLLEventRepository(KTLLDatabase database) {
        return new KTLLEventRepository(application, database.KTLLEventDao());
    }

    @Singleton
    @Provides
    public EventTagRepository provideEventTagRepository(KTLLDatabase database) {
        return new EventTagRepository(application, database.eventTagDao());
    }

    @Singleton
    @Provides
    public TagRepository provideTagRepository(KTLLDatabase database) {
        return new TagRepository(application, database.tagDao());
    }
}
