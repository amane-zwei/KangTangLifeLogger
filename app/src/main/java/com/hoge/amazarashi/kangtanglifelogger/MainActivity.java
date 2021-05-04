package com.hoge.amazarashi.kangtanglifelogger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hoge.amazarashi.kangtanglifelogger.fragments.InputFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<OnPermissionResponse> onPermissionResponses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int viewId = View.generateViewId();
        FrameLayout layout = new FrameLayout(this);
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setId(viewId);
        this.setContentView(layout);

        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            transaction.add(layout.getId(), new InputFragment());

            transaction.commit();
        }
    }

    public void addPermissionRequest(OnPermissionResponse onPermissionResponse) {
        onPermissionResponses.add(onPermissionResponse);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int index = onPermissionResponses.size() - 1; index >= 0; index--) {
            OnPermissionResponse onPermissionResponse = onPermissionResponses.get(index);
            if (onPermissionResponse.apply(requestCode, permissions, grantResults)) {
                onPermissionResponses.remove(index);
            }
        }
    }

    public interface OnPermissionResponse {
        boolean apply(int requestCode, String[] permissions, int[] grantResults);
    }
}
