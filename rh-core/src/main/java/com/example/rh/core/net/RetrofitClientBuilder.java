package com.example.rh.core.net;

import android.content.Context;

import com.example.rh.core.net.callback.IError;
import com.example.rh.core.net.callback.IFailure;
import com.example.rh.core.net.callback.IRequest;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author RH
 * @date 2018/9/28
 */
public class RetrofitClientBuilder {
    private Map<String, Object> PARAMS = RetrofitCreator.getParams();
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private File file = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;

    public final RetrofitClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public final RetrofitClientBuilder params(WeakHashMap<String, Object> mParams) {
        PARAMS.putAll(mParams);
        return this;
    }

    public final RetrofitClientBuilder params(String key, Object valus) {
        PARAMS.put(key, valus);
        return this;
    }

    public final RetrofitClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RetrofitClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RetrofitClientBuilder file(File file) {
        this.file = file;
        return this;
    }

    public final RetrofitClientBuilder file(String filePath) {
        this.file = new File(filePath);
        return this;
    }

    public final RetrofitClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RetrofitClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RetrofitClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RetrofitClientBuilder loader(Context context) {
        this.mContext = context;
        //默认一种Dialog样式
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RetrofitClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RetrofitClient build() {
        return new RetrofitClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody, file, mLoaderStyle, mContext);
    }
}
