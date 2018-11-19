package com.example.rh.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * @author RH
 * @date 2018/10/29
 * <p>
 * RecyclerView 分割线
 */
public class BaseDecoration extends DividerItemDecoration {
    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
