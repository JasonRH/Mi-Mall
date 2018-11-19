package com.example.rh.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.rh.core.app.MyApp;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.utils.log.MyLogger;
import com.example.rh.ui.recycler.DataConverter;
import com.example.rh.ui.recycler.MultipleItemEntity;
import com.example.rh.ui.recycler.MultipleRecyclerAdapter;

import java.util.ArrayList;

/**
 * @author RH
 * @date 2018/10/25
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    private RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView recyclerview, DataConverter converter, PagingBean bean) {
        REFRESH_LAYOUT = refreshLayout;
        BEAN = bean;
        RECYCLERVIEW = recyclerview;
        CONVERTER = converter;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    /**
     * 简单工厂方法
     */
    public static RefreshHandler create(SwipeRefreshLayout refreshLayout,
                                        RecyclerView recyclerview,
                                        DataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerview, converter, new PagingBean());
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        MyApp.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        BEAN.setmDelayed(1000);
        RetrofitClient
                .builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //Toast.makeText(MyApp.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setmTotal(object.getInteger("total"))
                                .setmPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        //加载更多
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    private void paging(final String url) {
        final int pageSize = BEAN.getmPageSize();
        final int currentCount = BEAN.getmCurrentCount();
        final int total = BEAN.getmTotal();
        final int index = BEAN.getmPageIndex();

        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            MyApp.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RetrofitClient.builder()
                            .url(url + index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    MyLogger.json("paging", response);
                                    ArrayList<MultipleItemEntity> data = CONVERTER.setJsonData(response).convert();
                                    if (data != null && data.size() > 0) {
                                        CONVERTER.clearData();
                                        mAdapter.addData(data);
                                        //累加数量
                                        BEAN.setmCurrentCount(mAdapter.getData().size());
                                        mAdapter.loadMoreComplete();
                                        BEAN.addIndex();
                                    } else {
                                        MyLogger.e("下一页", "暂无数据");
                                    }

                                }
                            })
                            .build()
                            .get();
                }
            }, 1000);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        paging("Mall?index=");
    }
}
