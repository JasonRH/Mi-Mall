package com.example.rh.ec.main.personal.profile;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ec.main.personal.list.ListAdapter;
import com.example.rh.ec.main.personal.list.ListBean;
import com.example.rh.ec.main.personal.list.ListItemType;
import com.example.rh.ec.main.personal.settings.NameFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/11/7
 * <p>
 * 个人信息设置
 */
public class UserProfileFragment extends BaseAppFragment {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_user_profile;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();

        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setmFragment(new NameFragment())
                .setValue("未设置姓名")
                .build();

        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build();

        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
