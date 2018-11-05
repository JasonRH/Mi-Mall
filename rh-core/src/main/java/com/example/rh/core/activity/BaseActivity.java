package com.example.rh.core.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.widget.FrameLayout;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh_core.R;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author RH
 * @date 2018/8/22
 * <p>
 * 继承 fragmentation 中的Activity
 */
public abstract class BaseActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //代码创建FrameLayout布局
        initContainer(savedInstanceState);
    }

    private void initContainer(Bundle savedInstanceState) {
        //V7 包中的FrameLayout有异常,可以使用FrameLayout
        @SuppressLint("RestrictedApi") final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if (savedInstanceState == null) {
            //加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
            //fragmentation 中封装的方法
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    /**
     * 传入第一个Fragment
     */
    protected abstract BaseAppFragment setRootDelegate();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
