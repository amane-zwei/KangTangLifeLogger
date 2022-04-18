package com.hoge.amazarashi.kangtanglifelogger.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService;
import com.hoge.amazarashi.kangtanglifelogger.viewmodel.EventViewModel;
import com.hoge.amazarashi.kangtanglifelogger.views.InputView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import lombok.Setter;

public class InputFragment extends Fragment {

    @Inject
    RegisterEventService registerEventService;

    @Setter
    private View targetView;

    private EventViewModel eventViewModel;

    private ActivityResultLauncher<String[]> requestLocationPermissionLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestLocationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), this::dispatchPermissionResponse);

        Context context = getContext();
        if (context == null) {
            return;
        }
        KTLLApplication application = (KTLLApplication) context.getApplicationContext();
        application.getApplicationComponent().inject(this);

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new InputView(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle){
        Context context = getContext();
        InputView inputView = (InputView) view;
        inputView.setOnSaveListener(this::onSave);
        inputView.attacheEventViewModel(eventViewModel);

        if (context == null) {
            return;
        }

//        if (eventViewModel.getValues().size() < 1) {
//            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                requestLocationPermissionLauncher.launch(
//                        new String[]{
//                                Manifest.permission.ACCESS_FINE_LOCATION,
//                                Manifest.permission.ACCESS_COARSE_LOCATION
//                        });
//            } else {
//                initLocation(inputView);
//            }
//        } else {
//            restore(inputView, eventViewModel.getValues());
//        }
    }

//    public EventViewModel.ValueViewModel createValue(InputValueView valueView) {
//        EventViewModel.ValueViewModel result = attacheValue(valueView, new EventViewModel.ValueViewModel());
//        eventViewModel.getValues().add(result);
//        return result;
//    }
//
//    private EventViewModel.ValueViewModel attacheValue(InputValueView valueView, EventViewModel.ValueViewModel target) {
//        target.tag.observe(getViewLifecycleOwner(), tag -> {
//                    valueView.getTagNameView().applyTag(tag);
//                }
//        );
//        target.value.observe(getViewLifecycleOwner(), value -> {
//                    valueView.getValueView().applyValue(value);
//                }
//        );
//        return target;
//    }

    private void restore(InputView inputView, List<EventViewModel.ValueViewModel> src) {
//        for(EventViewModel.ValueViewModel item : src) {
//            attacheValue(inputView.restore(item), item);
//        }
    }

//    @SuppressLint("MissingPermission")
//    private void dispatchPermissionResponse(Map<String, Boolean> resultMap) {
//        InputView mainView = (InputView) getView();
////        if (mainView == null || eventViewModel.getValues().size() > 0) {
////            return;
////        }
////        if (hasPermission(resultMap, Manifest.permission.ACCESS_FINE_LOCATION)
////                || hasPermission(resultMap, Manifest.permission.ACCESS_COARSE_LOCATION)) {
////            initLocation(mainView);
////        } else {
//            initNoLocation(mainView);
////        }
//    }

//    @androidx.annotation.RequiresPermission(anyOf = {
//            "android.permission.ACCESS_COARSE_LOCATION",
//            "android.permission.ACCESS_FINE_LOCATION"})
//    private void initLocation(InputView view) {
//        view.addLocation();
//        view.add();
//    }
//
//    private void initNoLocation(InputView view) {
//        view.add();
//    }


    private void onSave(RegisterEventService.EventRecord action) {
        //registerEventService.register(action, this::onComplete);
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
