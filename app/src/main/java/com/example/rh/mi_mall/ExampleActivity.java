package com.example.rh.mi_mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.example.rh.core.activity.BaseActivity;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.launcher.LauncherFragment;

/**
 * @author RH
 * @date 2018/10/17
 */
public class ExampleActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected BaseAppFragment setRootDelegate() {
        return new LauncherFragment();
    }
}
