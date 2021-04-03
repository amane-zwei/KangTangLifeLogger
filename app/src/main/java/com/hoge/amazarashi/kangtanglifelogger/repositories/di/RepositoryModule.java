package com.hoge.amazarashi.kangtanglifelogger.repositories.di;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.repositories.ActionTagRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLActionRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;

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
    public KTLLActionRepository provideKTLLActionRepository(KTLLDatabase database) {
        return new KTLLActionRepository(application, database.KTLLActionDao());
    }

    @Singleton
    @Provides
    public ActionTagRepository provideActionTagRepository(KTLLDatabase database) {
        return new ActionTagRepository(application, database.actionTagDao());
    }

    @Singleton
    @Provides
    public TagRepository provideTagRepository(KTLLDatabase database) {
        return new TagRepository(application, database.tagDao());
    }
}
