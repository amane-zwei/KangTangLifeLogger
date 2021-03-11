package com.hoge.amazarashi.kangtanglifelogger.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hoge.amazarashi.kangtanglifelogger.views.InputView;

public class InputFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new InputView(inflater.getContext());
    }
}
