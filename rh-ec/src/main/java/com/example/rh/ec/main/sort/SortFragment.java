package com.example.rh.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.ec.R;
import com.example.rh.ec.main.sort.content.ContentFragment;
import com.example.rh.ec.main.sort.list.VerticalListFragment;


/**
 * @author RH
 * @date 2018/10/24
 */
public class SortFragment extends BottomItemFragment {
    @Override
    protected Object setLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        //不建议将布局加载写在这，应在进入分类页面后加载
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        //点击分类导航栏时加载布局
        super.onLazyInitView(savedInstanceState);
        final VerticalListFragment listFragment = new VerticalListFragment();
        loadRootFragment(R.id.sort_list_container, listFragment);
        //设置右侧第一个分类显示，默认显示分类一
        loadRootFragment(R.id.sort_content_container, ContentFragment.newInstance(1));
    }
}
