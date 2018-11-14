package com.example.rh.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.core.ui.recycler.BaseDecoration;
import com.example.rh.core.ui.refresh.RefreshHandler;
import com.example.rh.core.utils.callback.CallbackManager;
import com.example.rh.core.utils.callback.CallbackType;
import com.example.rh.core.utils.callback.IGlobalCallback;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ec.main.EcBottomFragment;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

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

    private RefreshHandler mRefreshHandler = null;

    @OnClick(R2.id.fragment_index_icon_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getMyParentFragment());
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_index;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());

        //扫描二维码回调
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(String args) {
                        Toast.makeText(getContext(), "二维码信息是：" + args, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("json/mall/index.json");
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

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));

        //得到父布局
        final EcBottomFragment ecBottomFragment = getMyParentFragment();
        //传入ecBottomFragment，则打开和ecBottomFragment同级的页面，底部导航栏不可见
        //传入this，则打开和IndexFragment同级的页面，底部导航栏可见
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomFragment));
    }

}
