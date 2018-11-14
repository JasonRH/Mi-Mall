package com.example.rh.mi_mall;

import android.app.Application;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.Utils;
import com.example.rh.core.app.MyApp;
import com.example.rh.core.net.interceptors.AddCookieInterceptor;
import com.example.rh.core.net.interceptors.DebugInterceptor;
import com.example.rh.core.utils.callback.CallbackManager;
import com.example.rh.core.utils.callback.CallbackType;
import com.example.rh.core.utils.callback.IGlobalCallback;
import com.example.rh.core.utils.log.MyLogger;
import com.example.rh.ec.database.DatabaseManager;
import com.example.rh.ec.icon.MyFontAlibabaModule;
import com.example.rh.mi_mall.event.TestEvent;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.jpush.android.api.JPushInterface;

/**
 * @author RH
 * @date 2018/8/20
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.init(this)
                //.withApiHost("http://192.168.137.18:8080/myservlet/json/mall/")
                .withApiHost("http://10.203.70.146:8080/myservlet/json/mall/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new MyFontAlibabaModule())
                .withInterceptor(new DebugInterceptor("myTest", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                //添加与js映射的java对象 别名，该别名需要在js中使用
                .withJavascriptInterface("rh")
                //添加Event实现类，该类中的具体实现被js调用
                .withWebEvent("test", new TestEvent())
                //添加Cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                .withWebHost("https://www.toutiao.com/")
                .configure();

        //初始化logger
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化数据库
        DatabaseManager.getInstance().init(this);

        initStetho();

        //初始化AndroidUtilCode工具类
        Utils.init(this);

        //初始化极光推送
        // 设置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(true);
        //初始化 JPush
        JPushInterface.init(this);


        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(MyApp.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.resumePush(MyApp.getApplicationContext());
                            MyLogger.d("开启推送");
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(MyApp.getApplicationContext())) {
                            JPushInterface.stopPush(MyApp.getApplicationContext());
                            MyLogger.d("关闭推送");
                        }
                    }
                });
    }

    /**
     * 初始化Stetho，数据库查看工具
     */
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }
}
