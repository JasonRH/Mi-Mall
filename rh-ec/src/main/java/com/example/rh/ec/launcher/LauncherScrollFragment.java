package com.example.rh.ec.launcher;

import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.ui.launcher.LauncherHolderCreator;
import com.example.rh.ec.R;

import java.util.ArrayList;

/**
 * @author RH
 * @date 2018/10/18
 */
public class LauncherScrollFragment extends BaseAppFragment implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> images = new ArrayList<>();

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

    }
}
