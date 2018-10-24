package com.example.rh.mi_mall;

import android.app.Application;

import com.example.rh.core.app.MyApp;
import com.example.rh.core.net.interceptors.DebugInterceptor;
import com.example.rh.ec.database.DatabaseManager;
import com.example.rh.ec.icon.MyFontAlibabaModule;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

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
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();

        //初始化logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化数据库
        DatabaseManager.getInstance().init(this);

        initStetho();
    }

    //初始化Stetho，数据库查看工具
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }
}
