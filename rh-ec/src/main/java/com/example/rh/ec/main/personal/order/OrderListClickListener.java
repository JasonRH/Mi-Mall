package com.example.rh.ec.main.personal.order;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.rh.core.fragment.BaseAppFragment;

/**
 * @author RH
 * @date 2018/11/12
 */
public class OrderListClickListener extends SimpleClickListener {

    private final BaseAppFragment fragment;

    public OrderListClickListener(BaseAppFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        fragment.getSupportDelegate().start(new OrderCommentFragment());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
