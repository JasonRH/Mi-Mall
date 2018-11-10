package com.example.rh.ec.main.personal.settings;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/11/10
 */
public class AboutFragment extends BaseAppFragment {

    @BindView(R2.id.tv_info)
    AppCompatTextView mTextView = null;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_about;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        RetrofitClient.builder()
                .url("about.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextView.setText(info);
                    }
                })
                .build()
                .get();
    }

}
