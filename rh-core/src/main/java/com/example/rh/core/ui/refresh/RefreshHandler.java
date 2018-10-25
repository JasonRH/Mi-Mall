package com.example.rh.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.example.rh.core.app.MyApp;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;

/**
 * @author RH
 * @date 2018/10/25
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
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
        RetrofitClient
                .builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(MyApp.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

}
