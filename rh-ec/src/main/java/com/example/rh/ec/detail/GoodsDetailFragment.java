package com.example.rh.ec.detail;

import android.os.Bundle;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author RH
 * @date 2018/10/30
 */
public class GoodsDetailFragment extends BaseAppFragment {


    public static GoodsDetailFragment create() {
        return new GoodsDetailFragment();
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_goods_detail;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
