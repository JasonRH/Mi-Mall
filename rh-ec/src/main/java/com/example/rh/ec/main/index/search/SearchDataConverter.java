package com.example.rh.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.example.rh.core.ui.recycler.DataConverter;
import com.example.rh.core.ui.recycler.MultipleFields;
import com.example.rh.core.ui.recycler.MultipleItemEntity;
import com.example.rh.core.utils.storage.MyPreference;

import java.util.ArrayList;

/**
 * @author RH
 * @date 2018/11/15
 */
public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = MyPreference.getCustomAppProfile(TAG_SEARCH_HISTORY);

        if (!"".equals(jsonStr)) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
