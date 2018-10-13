package com.example.rh.mi_mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.rh.core.app.MyApp;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.RetrofitCreator;
import com.example.rh.core.net.callback.IError;
import com.example.rh.core.net.callback.IFailure;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.ui.LoaderStyle;

import java.util.WeakHashMap;

/**
 * @author RH
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(MyApp.getApplicationContext(), "这是全局Context", Toast.LENGTH_SHORT).show();
        //testRetrofitClient();
    }

    private void testRetrofitClient() {
        RetrofitClient.builder()
                .url("https://www.baidu.com/")
                .loader(this)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(MyApp.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
