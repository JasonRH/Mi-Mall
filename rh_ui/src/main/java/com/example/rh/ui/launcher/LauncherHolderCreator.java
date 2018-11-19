package com.example.rh.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.rh_core.R;

/**
 * @author RH
 * @date 2018/10/18
 *
 * 自定义Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
 */
public class LauncherHolderCreator implements CBViewHolderCreator{
    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.banner;
    }
}
