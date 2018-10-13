package com.example.rh.core.net;

import com.example.rh.core.app.ConfigType;
import com.example.rh.core.app.MyApp;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author RH
 * @date 2018/9/28
 */
public class RetrofitCreator {
    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    /**
     * 使用时记得clear
     * 浅拷贝，引用的对象更改后，原WeakHashMap的值也会更改
     * a = b ,浅拷贝，指向同一内存地址
     * a.putAll(b),深拷贝,a和b完全独立，不指向同一内存地址
     */
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * Service接口
     */
    public static RetrofitService getRetrofitService() {
        return RetrofitServiceHolder.RETROFIT_SERVICE;
    }

    private static final class RetrofitServiceHolder {
        private static final RetrofitService RETROFIT_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RetrofitService.class);
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = MyApp.getConfiguration(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 10;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                //不加这行，连接超时后会再重试一次，实际超时时间为TIME_OUT*2
                .retryOnConnectionFailure(false)
                .build();
    }


}
