package com.example.rh.ui.banner;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.rh_core.R;

/**
 * @author RH
 * @date 2018/10/26
 */
public class HolderCreator implements CBViewHolderCreator{

    @Override
    public Holder createHolder(View itemView) {
        return new ImageHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.banner;
    }
}
