package com.example.rh.ec.main.personal.address;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.ui.recycler.MultipleItemEntity;
import com.example.rh.core.utils.log.MyLogger;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/11/8
 */
public class AddressFragment extends BaseAppFragment implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_address;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        RetrofitClient.builder()
                .url("address.json")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        MyLogger.d("AddressDelegate", response);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
