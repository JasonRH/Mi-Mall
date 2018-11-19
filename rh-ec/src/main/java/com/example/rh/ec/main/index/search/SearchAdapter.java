package com.example.rh.ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.example.rh.ui.recycler.MultipleFields;
import com.example.rh.ui.recycler.MultipleItemEntity;
import com.example.rh.ui.recycler.MultipleRecyclerAdapter;
import com.example.rh.ui.recycler.MultipleViewHolder;
import com.example.rh.ec.R;

import java.util.List;

/**
 * @author RH
 * @date 2018/11/15
 */
public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
