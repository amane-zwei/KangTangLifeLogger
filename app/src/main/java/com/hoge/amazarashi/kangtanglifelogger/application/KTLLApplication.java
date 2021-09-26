package com.hoge.amazarashi.kangtanglifelogger.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import com.hoge.amazarashi.kangtanglifelogger.client.LocationClient;
import com.hoge.amazarashi.kangtanglifelogger.database.KTLLDatabaseModule;
import com.hoge.amazarashi.kangtanglifelogger.domain.TagList;
import com.hoge.amazarashi.kangtanglifelogger.repositories.di.RepositoryModule;
import com.hoge.amazarashi.kangtanglifelogger.service.di.ServiceModule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Getter;

public class KTLLApplication extends Application {

    @Getter
    private KTLLApplicationComponent applicationComponent;

    @Getter
    private TagList tagList;

    @Getter
    private ExecutorService executorService;

    @Getter
    private LocationClient locationClient;

    @Getter
    private Handler mainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        executorService = Executors.newCachedThreadPool();
        locationClient = new LocationClient(this);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

        applicationComponent = DaggerKTLLApplicationComponent
                .builder()
                .kTLLDatabaseModule(new KTLLDatabaseModule(this))
                .repositoryModule(new RepositoryModule(this))
                .serviceModule(new ServiceModule(this))
                .build();

        tagList = new TagList(applicationComponent);
    }
}
