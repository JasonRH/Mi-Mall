package com.example.rh.mi_mall;

import com.example.rh.core.activity.BaseActivity;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.launcher.LauncherFragment;
import com.example.rh.ec.launcher.LauncherScrollFragment;

/**
 * @author RH
 * @date 2018/10/17
 */
public class ExampleActivity extends BaseActivity {
    @Override
    protected BaseAppFragment setRootDelegate() {
        return new LauncherScrollFragment();
    }
}
