package com.example.rh.core.fragment.web.client;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.rh.core.app.MyApp;
import com.example.rh.core.fragment.web.BaseWebFragment;
import com.example.rh.core.fragment.web.IPageLoadListener;
import com.example.rh.core.fragment.web.route.Router;
import com.example.rh.core.ui.loader.MyLoader;
import com.example.rh.core.utils.log.MyLogger;

/**
 * @author RH
 * @date 2018/11/2
 * <p>
 * 帮助WebView处理各种通知、请求事件
 */
public class WebViewClientImpl extends WebViewClient {
    private final BaseWebFragment fragment;
    private IPageLoadListener mPageLoadListener = null;

    public WebViewClientImpl(BaseWebFragment fragment) {
        this.fragment = fragment;
    }

    public void setPageLoadListener(IPageLoadListener loadListener) {
        this.mPageLoadListener = loadListener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        MyLogger.d("shouldOverrideUrlLoading", url);
        //根据url类型，做出相应的处理
        return Router.getInstance().handleWebUrl(fragment, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mPageLoadListener != null) {
            mPageLoadListener.onLoadStart();
        }
        MyLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mPageLoadListener != null) {
            mPageLoadListener.onLoadFinish();
        }
        MyApp.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyLoader.stopLoading();
            }
        },1000);
    }
}
