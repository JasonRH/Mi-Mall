package com.example.rh.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.ui.recycler.MultipleItemEntity;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ec.main.sort.SortFragment;

import java.util.List;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/10/30
 */
public class VerticalListFragment extends BaseAppFragment {
    @BindView(R2.id.sort_list_vertical_menu)
    RecyclerView mRecyclerView;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_vertical_list;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RetrofitClient.builder()
                .url("json/mall/sort_list.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(response).convert();
                        final SortFragment fragment = getMyParentFragment();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, fragment);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }

    private final void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }
}
