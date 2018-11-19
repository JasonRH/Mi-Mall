package com.example.rh.ui.launcher;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.rh_core.R;


/**
 * @author RH
 * @date 2018/10/18
 */
public class LauncherHolder extends Holder<Integer> {
    private AppCompatImageView mImageView;

    public LauncherHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = (AppCompatImageView) itemView.findViewById(R.id.banner_image);
    }

    @Override
    public void updateUI(Integer data) {
        //滑到当前页后的操作
        mImageView.setBackgroundResource(data);
    }
}
