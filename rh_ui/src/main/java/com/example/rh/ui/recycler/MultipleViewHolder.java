package com.example.rh.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author RH
 * @date 2018/10/26
 */
public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
