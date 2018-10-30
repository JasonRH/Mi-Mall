package com.example.rh.mi_mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rh.core.app.MyApp;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.IError;
import com.example.rh.core.net.callback.IFailure;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.net_rx.RxRetrofitClient;
import com.example.rh.core.net_rx.RxRetrofitCreator;
import com.example.rh.core.utils.log.MyLogger;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author RH
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testRetrofitClient();
        //onCallRxGet1();
        //onCallRxGet2();
    }

    //TODO:测试网络，可删除
    private void testRetrofitClient() {
        String url = "";
        WeakHashMap<String, Object> paramMap = new WeakHashMap<>();
        paramMap.put("user", "kyjwh1");
        paramMap.put("passwd", "888888");
        paramMap.put("vmId", "53-1708-0005");
        /*paramMap.put("user", "whuser");
        paramMap.put("passwd", "888888");
        paramMap.put("vmId", "18-1711-0001");*/
        RetrofitClient.builder()
                .url(url)
                .params(paramMap)
                .loader(this)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(MyApp.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        MyLogger.d("onSuccess: " + response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        MyLogger.e("测试网络", "onFailure: ");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        MyLogger.e("测试网络", "onError: ");
                    }
                })
                .build()
                .post();
    }

    //TODO:测试 RxJava+Retrofit，方案一（最简洁），可删除
    private void onCallRxGet1() {
        final String url = "https://www.baidu.com";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();
        final Observable<String> observable = RxRetrofitCreator.getRxRetrofitService().get(url, params);
        observable.subscribeOn(Schedulers.io())
                //下载时注意，不能使用主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(MyApp.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO:测试 RxJava+Retrofit，方案二(采用建造者模式)，可删除
    private void onCallRxGet2() {
        final String url = "https://www.baidu.com";
        RxRetrofitClient
                .builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                //下载时注意，不能使用主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(MyApp.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
