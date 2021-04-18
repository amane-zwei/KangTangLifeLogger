package com.hoge.amazarashi.kangtanglifelogger.repositories.di;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabase;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.ValueRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLActionRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.ItemRepository;

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
    public ValueRepository provideValueRepository(KTLLDatabase database) {
        return new ValueRepository(application, database.valueDao());
    }

    @Singleton
    @Provides
    public TagRepository provideTagRepository(KTLLDatabase database) {
        return new TagRepository(application, database.tagDao());
    }

    @Singleton
    @Provides
    public ItemRepository provideItemRepository(KTLLDatabase database) {
        return new ItemRepository(application, database.itemDao());
    }
}
