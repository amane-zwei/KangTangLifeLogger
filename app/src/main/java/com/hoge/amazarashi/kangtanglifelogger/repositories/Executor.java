package com.hoge.amazarashi.kangtanglifelogger.repositories;

import java.util.concurrent.Executors;

public class Executor {
    public static void IOThread(Runnable runnable) {
        Executors.newSingleThreadExecutor().execute(runnable);
    }
}
