package com.example.rh.ec.main.personal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ec.main.personal.list.ListAdapter;
import com.example.rh.ec.main.personal.list.ListBean;
import com.example.rh.ec.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author RH
 * @date 2018/10/24
 */
public class PersonalFragment extends BottomItemFragment {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
    }
}
