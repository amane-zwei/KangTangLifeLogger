package com.hoge.amazarashi.kangtanglifelogger.repositories.strorages;

import android.content.Context;
import android.net.Uri;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.repositories.ItemRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLActionRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.ValueRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;

public class BackupRepository {

    private static final String itemFileName = "item.json";
    private static final String actionFileName = "action.json";
    private static final String eventFileName = "event.json";
    private static final String tagFileName = "tag.json";
    private static final String valueFileName = "value.json";

    @Inject
    ItemRepository itemRepository;
    @Inject
    KTLLActionRepository ktllActionRepository;
    @Inject
    KTLLEventRepository ktllEventRepository;
    @Inject
    TagRepository tagRepository;
    @Inject
    ValueRepository valueRepository;

    public BackupRepository(KTLLApplication application) {
        application.getApplicationComponent().inject(this);
    }

    public void exportData(Context context, Uri uri) {
        save(context, uri);
    }

    public void importData() {

    }

    private void save(Context context, Uri uri) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                OutputStream oStream = context.getContentResolver().openOutputStream(uri);
                ZipOutputStream zipOutputStream = new ZipOutputStream(oStream);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                putFile(zipOutputStream, itemFileName, objectMapper.writeValueAsBytes(itemRepository.listAll()));
                putFile(zipOutputStream, actionFileName, objectMapper.writeValueAsBytes(ktllActionRepository.listAll()));
                putFile(zipOutputStream, eventFileName, objectMapper.writeValueAsBytes(ktllEventRepository.listAll()));
                putFile(zipOutputStream, tagFileName, objectMapper.writeValueAsBytes(tagRepository.listAll()));
                putFile(zipOutputStream, valueFileName, objectMapper.writeValueAsBytes(valueRepository.listAll()));

                zipOutputStream.close();
                oStream.close();
            } catch (IOException ignored) {
            }
        });
    }

    private void putFile(ZipOutputStream zipOutputStream, String fileName, byte[] data) throws IOException {
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(data);
    }

    private interface OutputFunction {
        Object apply();
    }
}
