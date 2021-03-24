package com.hoge.amazarashi.kangtanglifelogger.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.entities.KTLLEvent;
import com.hoge.amazarashi.kangtanglifelogger.entities.Tag;
import com.hoge.amazarashi.kangtanglifelogger.repositories.KTLLEventRepository;
import com.hoge.amazarashi.kangtanglifelogger.repositories.TagRepository;
import com.hoge.amazarashi.kangtanglifelogger.views.InputView;

import java.util.ArrayList;

import javax.inject.Inject;

public class InputFragment extends Fragment {

    @Inject
    KTLLEventRepository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        InputView inputView = new InputView(inflater.getContext());
        inputView.setOnSaveListener(this::onSave);

        KTLLApplication application = (KTLLApplication) inflater.getContext().getApplicationContext();
        application.getApplicationComponent().inject(this);

        return inputView;
    }

    private void onSave(KTLLEvent event) {
        event = new KTLLEvent(
                "20000101",
                "20991231");
        event.add(new Tag("hoge", "fuag"));
        repository.insert(event);
    }
}
