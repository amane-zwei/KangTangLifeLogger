package com.hoge.amazarashi.kangtanglifelogger.repositories.strorages;

import android.content.Context;
import android.net.Uri;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLAction;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Synonym;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.entities.Value;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLActionRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.SynonymRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.ValueRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;

public class BackupRepository {

    private static final String actionFileName = "action.json";
    private static final String eventFileName = "event.json";
    private static final String tagFileName = "tag.json";
    private static final String valueFileName = "value.json";
    private static final String synonymFileName = "synonym.json";

    @Inject
    KTLLActionRepository ktllActionRepository;
    @Inject
    KTLLEventRepository ktllEventRepository;
    @Inject
    TagRepository tagRepository;
    @Inject
    ValueRepository valueRepository;
    @Inject
    SynonymRepository synonymRepository;

    public BackupRepository(KTLLApplication application) {
        application.getApplicationComponent().inject(this);
    }

    public void exportData(Context context, Uri uri) {
        save(context, uri);
    }

    public void importData(Context context, Uri uri) {
        restore(context, uri);
    }

    private void save(Context context, Uri uri) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                OutputStream outputStream = context.getContentResolver().openOutputStream(uri);
                ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                putFile(zipOutputStream, actionFileName, objectMapper.writeValueAsBytes(ktllActionRepository.listAll()));
                putFile(zipOutputStream, eventFileName, objectMapper.writeValueAsBytes(ktllEventRepository.listAll()));
                putFile(zipOutputStream, tagFileName, objectMapper.writeValueAsBytes(tagRepository.listAll()));
                putFile(zipOutputStream, valueFileName, objectMapper.writeValueAsBytes(valueRepository.listAll()));
                putFile(zipOutputStream, synonymFileName, objectMapper.writeValueAsBytes(synonymRepository.listAll()));

                zipOutputStream.close();
                outputStream.close();
            } catch (IOException ignored) {
            }
        });
    }

    private void putFile(ZipOutputStream zipOutputStream, String fileName, byte[] data) throws IOException {
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(data);
    }

    private void restore(Context context, Uri uri) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                InputStream inputStream = context.getContentResolver().openInputStream(uri);
                ZipInputStream zipInputStream = new ZipInputStream(inputStream);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);

                // get project data
                ZipEntry zipEntry;
                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    String fileName = removeDirectory(zipEntry.getName());

                    switch (fileName) {
                        case actionFileName:
                            ktllActionRepository.replace(Arrays.asList(objectMapper.readValue(zipInputStream, KTLLAction[].class)));
                            break;
                        case eventFileName:
                            ktllEventRepository.replace(Arrays.asList(objectMapper.readValue(zipInputStream, KTLLEvent[].class)));
                            break;
                        case tagFileName:
                            tagRepository.replace(Arrays.asList(objectMapper.readValue(zipInputStream, Tag[].class)));
                            break;
                        case valueFileName:
                            valueRepository.replace(Arrays.asList(objectMapper.readValue(zipInputStream, Value[].class)));
                            break;
                        case synonymFileName:
                            synonymRepository.replace(Arrays.asList(objectMapper.readValue(zipInputStream, Synonym[].class)));
                            break;
                    }
                    zipInputStream.closeEntry();
                }
                zipInputStream.close();
                inputStream.close();
            } catch(IOException e) {
                android.util.Log.d("hoge", "error");
            }
        });
    }

    private String removeDirectory(String src) {
        int index = src.lastIndexOf('/');
        return index < 0 ? src : src.substring(index + 1);
    }
}
