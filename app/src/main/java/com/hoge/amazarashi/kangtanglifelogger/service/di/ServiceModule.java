package com.hoge.amazarashi.kangtanglifelogger.service.di;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;

@Module
@AllArgsConstructor
public class ServiceModule {

    private final KTLLApplication application;

    @Singleton
    @Provides
    public RegisterEventService provideRegisterEventService() {
        return new RegisterEventService(application);
    }
}
