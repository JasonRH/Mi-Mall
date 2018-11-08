package com.example.rh.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ec.main.personal.list.ListAdapter;
import com.example.rh.ec.main.personal.list.ListBean;
import com.example.rh.ec.main.personal.list.ListItemType;
import com.example.rh.ec.main.personal.order.OrderListFragment;
import com.example.rh.ec.main.personal.profile.UserProfileFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author RH
 * @date 2018/10/24
 */
public class PersonalFragment extends BottomItemFragment {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings;
    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle bundle = null;

    private void startOrderListByTppe() {
        final OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(bundle);
        //在EcBottomFragment的基础上打开fragment，不需要展示底部导航栏
        getMyParentFragment().getSupportDelegate().start(fragment);
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        //点击全部订单
        bundle.putString(ORDER_TYPE, "all");
        startOrderListByTppe();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        //点击头像
        getMyParentFragment().getSupportDelegate().start(new UserProfileFragment());
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
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
