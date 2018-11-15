package com.example.rh.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.ui.recycler.MultipleFields;
import com.example.rh.core.ui.recycler.MultipleItemEntity;
import com.example.rh.core.ui.refresh.PagingBean;
import com.example.rh.ec.detail.GoodsDetailFragment;

/**
 * @author RH
 * @date 2018/10/30
 */
public class IndexItemClickListener extends SimpleClickListener {

    private final BaseAppFragment FRAGMENT;

    private IndexItemClickListener(BaseAppFragment fragment) {
        FRAGMENT = fragment;
    }

    public static IndexItemClickListener create(BaseAppFragment fragment) {
        return new IndexItemClickListener(fragment);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodId = entity.getField(MultipleFields.ID);
        final GoodsDetailFragment fragment = GoodsDetailFragment.create(goodId);
        FRAGMENT.start(fragment);
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
