package com.example.rh.core.net;

import android.content.Context;
import android.util.Log;

import com.example.rh.core.net.callback.IError;
import com.example.rh.core.net.callback.IFailure;
import com.example.rh.core.net.callback.IRequest;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.net.callback.RequestCallbacks;
import com.example.rh.core.net.download.DownloadHandler;
import com.example.rh.core.ui.loader.LoaderStyle;
import com.example.rh.core.ui.loader.MyLoader;
import com.example.rh.core.utils.log.MyLogger;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author RH
 * @date 2018/8/27
 */
public class RetrofitClient {
    private final String URL;
    /**
     * 浅拷贝
     */
    private final WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    /**
     * 上传文件
     */
    private final File FILE;
    /**
     * 下载
     */
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    /**
     * 进度条样式
     */
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RetrofitClient(String url,
                          Map<String, Object> params,
                          IRequest iRequest,
                          ISuccess iSuccess,
                          IFailure iFailure,
                          IError iError,
                          RequestBody body,
                          File file,
                          String downloadDir,
                          String extension,
                          String name,
                          LoaderStyle style,
                          Context context) {
        this.URL = url;
        //深拷贝
        PARAMS.putAll(params);
        this.REQUEST = iRequest;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
        this.BODY = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.LOADER_STYLE = style;
        this.CONTEXT = context;
    }

    public static RetrofitClientBuilder builder() {
        return new RetrofitClientBuilder();
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
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
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
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

    /**
     * 下载
     */
    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }

    /**
     * 上传
     */
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }
}
