package com.example.rh.core.net.download;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.rh.core.net.RetrofitCreator;
import com.example.rh.core.net.callback.IError;
import com.example.rh.core.net.callback.IFailure;
import com.example.rh.core.net.callback.IRequest;
import com.example.rh.core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author RH
 * @date 2018/10/12
 */
public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url,
                           IRequest iRequest,
                           String downDir,
                           String extension,
                           String name,
                           ISuccess iSuccess,
                           IFailure iFailure,
                           IError iError) {
        this.URL = url;
        this.REQUEST = iRequest;
        this.DOWNLOAD_DIR = downDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;

    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RetrofitCreator.getRetrofitService()
                .download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask fileTask = new SaveFileTask(REQUEST, SUCCESS);
                            //以线程池形式执行异步操作
                            fileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, responseBody, NAME);
                            //DOWNLOAD_DIR, EXTENSION, responseBody, NAME对应doInBackground()方法里面的参数

                            //这里一定要判断，否则文件下载不全
                            if (fileTask.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                        //清除参数容器
                        RetrofitCreator.getParams().clear();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                            RetrofitCreator.getParams().clear();
                        }
                    }
                });

    }
}
