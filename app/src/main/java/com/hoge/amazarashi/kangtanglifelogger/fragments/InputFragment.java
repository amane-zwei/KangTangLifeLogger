package com.hoge.amazarashi.kangtanglifelogger.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.strorages.BackupRepository;
import com.hoge.amazarashi.kangtanglifelogger.views.InputView;

import java.io.File;

import javax.inject.Inject;

public class InputFragment extends Fragment {

    @Inject
    KTLLEventRepository repository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        KTLLApplication application = (KTLLApplication) getContext().getApplicationContext();
        application.getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        InputView inputView = new InputView(inflater.getContext());
        inputView.setOnSaveListener(this::onSave);

        return inputView;
    }

    private void onSave(KTLLEvent event) {
        repository.insert(event);
    }
}
