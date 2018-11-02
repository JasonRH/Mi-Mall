package com.example.rh.core.fragment.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.rh.core.fragment.web.BaseWebFragment;
import com.example.rh.core.fragment.web.route.Router;
import com.example.rh.core.utils.log.MyLogger;

/**
 * @author RH
 * @date 2018/11/2
 * <p>
 * 帮助WebView处理各种通知、请求事件
 */
public class WebViewClientImpl extends WebViewClient {
    private final BaseWebFragment fragment;

    public WebViewClientImpl(BaseWebFragment fragment) {
        this.fragment = fragment;
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
}
