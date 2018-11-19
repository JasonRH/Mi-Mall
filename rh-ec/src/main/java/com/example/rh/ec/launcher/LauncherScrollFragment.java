package com.example.rh.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.rh.core.app.user.AccountManager;
import com.example.rh.core.app.user.IUserChecker;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ui.launcher.ILauncherListener;
import com.example.rh.ui.launcher.LauncherHolderCreator;
import com.example.rh.ui.launcher.OnLauncherFinishTag;
import com.example.rh.ui.launcher.ScrollLauncherTag;
import com.example.rh.core.utils.storage.MyPreference;
import com.example.rh.ec.R;

import java.util.ArrayList;

/**
 * @author RH
 * @date 2018/10/18
 */
public class LauncherScrollFragment extends BaseAppFragment implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    /**
     * 此处ArrayList不能被定义为静态，否则多次退出进入时，images会叠加
     */
    private final ArrayList<Integer> images = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;


    private void initBanner() {
        images.add(R.mipmap.launcher_01);
        images.add(R.mipmap.launcher_02);
        images.add(R.mipmap.launcher_03);
        images.add(R.mipmap.launcher_04);
        images.add(R.mipmap.launcher_05);
        mConvenientBanner.setPages(new LauncherHolderCreator(), images)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                //指示器位置居中
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                //是否可循环
                .setCanLoop(false);
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
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击最后一张图
        if (position == images.size() - 1) {
            MyPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
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
