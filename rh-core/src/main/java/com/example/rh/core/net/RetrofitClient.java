package com.example.rh.core.net;

import android.content.Context;
import android.util.Log;

import com.example.rh.core.net.callback.IError;
import com.example.rh.core.net.callback.IFailure;
import com.example.rh.core.net.callback.IRequest;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.net.callback.RequestCallbacks;
import com.example.rh.core.ui.LoaderStyle;
import com.example.rh.core.ui.MyLoader;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author RH
 * @date 2018/8/27
 */
public class RetrofitClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RetrofitClient(String url,
                          Map<String, Object> params,
                          IRequest iRequest,
                          ISuccess iSuccess,
                          IFailure iFailure,
                          IError iError,
                          RequestBody body,
                          LoaderStyle style,
                          Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = iRequest;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
        this.BODY = body;
        this.LOADER_STYLE = style;
        this.CONTEXT = context;
    }

    public static RetrofitClientBuilder builder() {
        return new RetrofitClientBuilder();
    }

    public final void get() {
        request(HttpMethod.GET);
        Log.e("get", "get: ");
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    private void request(HttpMethod method) {
        final RetrofitService service = RetrofitCreator.getRetrofitService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        //加载进度条Dialog
        if (LOADER_STYLE != null) {
            MyLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
        }

        if (call != null) {
            //结果回调，并在回调中取消Dialog（可以拿出来）
            call.enqueue(getRequestCallback());
        }
    }

    /**
     * 处理回调
     */
    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

}
