package com.example.rh.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.example.rh.core.app.user.AccountManager;
import com.example.rh.core.app.user.IUserChecker;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ui.launcher.ILauncherListener;
import com.example.rh.ui.launcher.OnLauncherFinishTag;
import com.example.rh.ui.launcher.ScrollLauncherTag;
import com.example.rh.core.utils.storage.MyPreference;
import com.example.rh.core.utils.timer.BaseTimerTask;
import com.example.rh.core.utils.timer.ITimerListener;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author RH
 * @date 2018/10/17
 */
public class LauncherFragment extends BaseAppFragment implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //将Activity与接口关联
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_launcher;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        initTimer();
        Log.e("LauncherFragment", "onBindView: ");
    }

    @Override
    public void onTimer() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }


    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }

    /**
     * 判断是否显示滑动启动页
     */
    private void checkIsShowScroll() {
        if (!MyPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            //start(new LauncherScrollFragment(), SINGLETASK);
            //将自身从返回栈中移除，避免可返回后退
            getSupportDelegate().startWithPop(new LauncherScrollFragment());
        } else {
            //检查用户是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
