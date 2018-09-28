package com.example.rh.core.net;

import com.example.rh.core.net.callback.IError;
import com.example.rh.core.net.callback.IFailure;
import com.example.rh.core.net.callback.IRequest;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.net.callback.RequestCallbacks;

import java.util.Map;
import java.util.PropertyResourceBundle;
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

    public RetrofitClient(String url,
                          Map<String, Object> params,
                          IRequest iRequest,
                          ISuccess iSuccess,
                          IFailure iFailure,
                          IError iError,
                          RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = iRequest;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
        this.BODY = body;
    }

    public static RetrofitClientBuilder builder() {
        return new RetrofitClientBuilder();
    }

    private void request(HttpMethod method) {
        final RetrofitService service = RetrofitCreator.getRetrofitService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
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
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    public final void get() {
        request(HttpMethod.GET);
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

}
