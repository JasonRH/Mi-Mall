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

    public static RetrofitService getRetrofitService() {
        return RetrofitServiceHolder.RETROFIT_SERVICE;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = MyApp.getConfiguration(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder {
        private static final int TIME_OUT = 10;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                //不加这行，连接超时后会再重试一次，实际超时时间为TIME_OUT*2
                .retryOnConnectionFailure(false)
                .build();
    }

    private static final class RetrofitServiceHolder {
        private static final RetrofitService RETROFIT_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RetrofitService.class);
    }

    /**
     * 使用时记得clear
     */
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }
}