package com.example.rh.mi_mall;

import android.app.Application;

import com.example.rh.core.app.MyApp;
import com.example.rh.core.net.interceptors.DebugInterceptor;
import com.example.rh.ec.icon.MyFontAlibabaModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * @author RH
 * @date 2018/8/20
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new MyFontAlibabaModule())
                .withInterceptor(new DebugInterceptor("myTest", R.raw.test))
                .configure();
    }
}
