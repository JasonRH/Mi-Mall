package com.example.rh.mi_mall.event;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.rh.core.fragment.web.event.Event;
import com.example.rh.core.utils.log.MyLogger;

/**
 * @author RH
 * @date 2018/11/2
 */
public class TestEvent extends Event {
    private final String ACTION = "test";
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
        if (ACTION.equals(getAction())) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    //调用js方法
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
