package com.hoge.amazarashi.kangtanglifelogger.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class KTLLApplicationModule {
    private final Application application;

    public KTLLApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return application;
    }
}
