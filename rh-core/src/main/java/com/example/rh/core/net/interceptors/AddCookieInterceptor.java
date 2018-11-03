package com.example.rh.core.net.interceptors;

import android.util.Log;

import com.example.rh.core.utils.storage.MyPreference;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author RH
 * @date 2018/11/3
 */
public class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        String cookie = MyPreference.getCustomAppProfile("cookie");
        if (!"".equals(cookie)) {
            //给原生API请求附带上WebView拦截下来的Cookie
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build());
    }
}
