package com.example.rh.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.rh.core.fragment.bottom.BaseBottomFragment;
import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.core.ui.refresh.RefreshHandler;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/10/24
 */
public class IndexFragment extends BottomItemFragment {

    @BindView(R2.id.fragment_index_recycler)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.fragment_index_refresh)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.fragment_index_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.fragment_index_icon_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.fragment_index_edit_search)
    AppCompatEditText mSearchView = null;

    private RefreshHandler refreshHandler = null;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_index;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        refreshHandler = new RefreshHandler(mRefreshLayout);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //设置大小渐变及偏移量
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

}
