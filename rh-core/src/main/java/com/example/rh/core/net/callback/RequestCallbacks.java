package com.example.rh.core.net.callback;

import android.util.Log;

import com.example.rh.core.ui.LoaderStyle;
import com.example.rh.core.ui.MyLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author RH
 * @date 2018/9/28
 */
public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    //使用静态，避免内存泄露
    private static final android.os.Handler hanlder = new android.os.Handler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.e("get", "onResponse: ");
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.e("get", "onFailure: ");
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        onRequestFinish();
    }


    private void onRequestFinish() {
        //取消进度条Dialog
        if (LOADER_STYLE != null) {
            hanlder.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MyLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
