package com.hoge.amazarashi.kangtanglifelogger.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService;
import com.hoge.amazarashi.kangtanglifelogger.views.InputView;

import java.util.Map;

import javax.inject.Inject;

import lombok.Setter;

public class InputFragment extends Fragment {

    @Inject
    RegisterEventService registerEventService;

    @Setter
    private View targetView;

    private ActivityResultLauncher<String[]> requestLocationPermissionLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestLocationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), this::dispatchPermissionResponse);

        Context context = getContext();
        if (context == null) {
            return;
        }
        KTLLApplication application = (KTLLApplication) context.getApplicationContext();
        application.getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();

        InputView inputView = new InputView(context);
        inputView.setOnSaveListener(this::onSave);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermissionLauncher.launch(
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    });
        } else {
            initLocation(inputView);
        }

        return inputView;
    }

    @SuppressLint("MissingPermission")
    private void dispatchPermissionResponse(Map<String, Boolean> resultMap) {
        InputView mainView = (InputView) getView();
        if (mainView == null) {
            return;
        }
        if (hasPermission(resultMap, Manifest.permission.ACCESS_FINE_LOCATION)
                || hasPermission(resultMap, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            initLocation(mainView);
        } else {
            initNoLocation(mainView);
        }
    }

    @androidx.annotation.RequiresPermission(anyOf = {
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"})
    private void initLocation(InputView view) {
        view.addLocation();
        view.add();
    }

    private void initNoLocation(InputView view) {
        view.add();
    }


    private void onSave(RegisterEventService.EventRecord action) {
        registerEventService.register(action, this::onComplete);
        next();
    }

    private void onComplete() {
//        Toast.makeText(getContext(), "on saved", Toast.LENGTH_LONG).show();
    }

    private void next() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        InputFragment inputFragment = new InputFragment();
        inputFragment.setTargetView(targetView);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.fade_out)
                .replace(targetView.getId(), inputFragment)
                .commit();
    }

    private boolean hasPermission(Map<String, Boolean> map, String key) {
        Boolean result = map.get(key);
        return result != null && result;
    }
}
