package com.example.rh.mi_mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.rh.core.activity.BaseActivity;
import com.example.rh.core.app.MyApp;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ui.launcher.ILauncherListener;
import com.example.rh.ui.launcher.OnLauncherFinishTag;
import com.example.rh.ec.launcher.LauncherFragment;
import com.example.rh.ec.main.EcBottomFragment;
import com.example.rh.ec.sign.ISignListener;
import com.example.rh.ec.sign.SignInFragment;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

/**
 * @author RH
 * @date 2018/10/17
 */
public class ExampleActivity extends BaseActivity implements
        ISignListener,
        ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //传入Activity，微信需要
        MyApp.getConfigurator().withActivity(this);
        //实现沉浸式状态栏
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected BaseAppFragment setRootDelegate() {
        return new LauncherFragment();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        getSupportDelegate().startWithPop(new EcBottomFragment());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        //启动图加载后，进入相应操作
        switch (tag) {
            case SIGNED:
                //用户已经登录
                getSupportDelegate().startWithPop(new EcBottomFragment());
                break;
            case NOT_SIGNED:
                //用户没登录,启动登录Fragment，并将自身从返回栈弹出
                //Start the target Fragment and pop itself
                getSupportDelegate().startWithPop(new SignInFragment());
                break;
            default:
                break;
        }
    }
}
