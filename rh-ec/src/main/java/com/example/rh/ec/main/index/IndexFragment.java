package com.example.rh.ec.main.index;

import android.os.Bundle;
import android.view.View;

import com.example.rh.core.fragment.bottom.BaseBottomFragment;
import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.ec.R;

/**
 * @author RH
 * @date 2018/10/24
 */
public class IndexFragment extends BottomItemFragment{
    @Override
    protected Object setLayout() {
        return R.layout.fragment_index;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
