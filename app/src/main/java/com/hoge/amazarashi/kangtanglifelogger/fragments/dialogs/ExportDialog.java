package com.hoge.amazarashi.kangtanglifelogger.fragments.dialogs;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.repositories.strorages.BackupRepository;

import java.io.File;

import javax.inject.Inject;

public class ExportDialog extends KTLLDialogFragment {

//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_CREATE_FILE = 1;
//    private static final String[] PERMISSIONS_STORAGE = {
////            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };

    public ExportDialog() {
        super();
    }

    @Inject
    BackupRepository backupRepository;

    @Override
    public @NonNull
    Dialog onCreateDialog(@Nullable Bundle bundle) {
        KTLLApplication application = (KTLLApplication) getContext().getApplicationContext();
        application.getApplicationComponent().inject(this);

        return super.onCreateDialog(bundle);
    }

    @Override
    public View createContentView(Context context) {
        return new ContentView(context);
    }

    public class ContentView extends LinearLayout {

        public ContentView(Context context) {
            super(context);

            setOrientation(VERTICAL);
            setBackgroundColor(0xffffffff);

            {
                Button button = new Button(context);
                button.setText("export");
                button.setOnClickListener(this::onBackup);
                addView(button);
            }
            {
                Button button = new Button(context);
                button.setText("import");
                addView(button);
            }
        }

        private void onBackup(View view) {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.setType("application/zip");
            intent.putExtra(Intent.EXTRA_TITLE, "ktll-backup.zip");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, "Create a file"), REQUEST_CREATE_FILE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    backupRepository.exportData(getContext(), uri);
                }
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }
}
