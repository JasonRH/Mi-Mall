package com.example.rh.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.ui.recycler.MultipleItemEntity;
import com.example.rh.core.utils.log.MyLogger;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ec.main.personal.PersonalFragment;

import java.util.List;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/11/6
 */
public class OrderListFragment extends BaseAppFragment {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        final Bundle bundle = getArguments();
        if (bundle != null) {
            mType = bundle.getString(PersonalFragment.ORDER_TYPE, "error");
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.e("OrderListFragment", "onLazyInitView: ");
        RetrofitClient.builder()
                .loader(getContext())
                .url("order_list.json")
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        MyLogger.d(response);
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
