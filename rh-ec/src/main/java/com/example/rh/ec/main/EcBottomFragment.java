package com.example.rh.ec.main;

import android.graphics.Color;

import com.example.rh.core.fragment.bottom.BaseBottomFragment;
import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.core.fragment.bottom.BottomTabBean;
import com.example.rh.core.fragment.bottom.ItemBuilder;
import com.example.rh.ec.main.cart.ShopCartFragment;
import com.example.rh.ec.main.discover.DiscoverFragment;
import com.example.rh.ec.main.index.IndexFragment;
import com.example.rh.ec.main.personal.PersonalFragment;
import com.example.rh.ec.main.sort.SortFragment;

import java.util.LinkedHashMap;

/**
 * @author RH
 * @date 2018/10/24
 */
public class EcBottomFragment extends BaseBottomFragment {
    @Override
    protected LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortFragment());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartFragment());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalFragment());
        return builder.addItem(items).build();
    }

    @Override
    protected int setIndexFragment() {
        return 0;
    }

    @Override
    protected int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
