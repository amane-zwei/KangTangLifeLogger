package com.hoge.amazarashi.kangtanglifelogger.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService;
import com.hoge.amazarashi.kangtanglifelogger.views.InputView;

import javax.inject.Inject;

public class InputFragment extends Fragment {

    @Inject
    RegisterEventService registerEventService;

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

    private void onSave(RegisterEventService.EventRecord action) {
        registerEventService.register(action);
    }
}
