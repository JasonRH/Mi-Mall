package com.example.rh.core.fragment.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.fragment.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author RH
 * @date 2018/11/1
 */
public abstract class MyWebFragment extends BaseAppFragment {
    private WebView mWebView = null;
    private ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebAvailable = false;

    public MyWebFragment() {
    }

    public abstract IWebViewInitialize setInitialize();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(RouteKeys.URL.name());
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitialize initialize = setInitialize();
            if (initialize != null) {
                //直接new出WebView,避免内存泄露
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initialize.initWebView(mWebView);
                mWebView.setWebViewClient(initialize.initWebViewClient());
                mWebView.setWebChromeClient(initialize.initWebChromeClient());
                mWebView.addJavascriptInterface(MyWebInterface.create(this), "rh");
                mIsWebAvailable = true;
            } else {
                throw new NullPointerException("IWebViewInitialize is null");
            }
        }
    }
}
