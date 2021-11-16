package com.hoge.amazarashi.kangtanglifelogger;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.hoge.amazarashi.kangtanglifelogger.fragments.InputFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        int viewId = View.generateViewId();
        FrameLayout layout = new FrameLayout(this);
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setId(viewId);
        this.setContentView(layout);

        if (savedInstanceState == null) {
            InputFragment inputFragment = new InputFragment();
            inputFragment.setTargetView(layout);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(layout.getId(), inputFragment)
                    .commit();
        }
    }
}
