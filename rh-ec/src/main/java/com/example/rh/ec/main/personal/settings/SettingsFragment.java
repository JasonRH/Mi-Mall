package com.example.rh.ec.main.personal.settings;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.utils.callback.CallbackManager;
import com.example.rh.core.utils.callback.CallbackType;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ec.main.personal.address.AddressFragment;
import com.example.rh.ec.main.personal.list.ListAdapter;
import com.example.rh.ec.main.personal.list.ListBean;
import com.example.rh.ec.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/11/10
 */
public class SettingsFragment extends BaseAppFragment {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;


    @Override
    protected Object setLayout() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        final ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            Toast.makeText(getContext(), "打开推送", Toast.LENGTH_SHORT).show();
                        } else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            Toast.makeText(getContext(), "关闭推送", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setText("消息推送")
                .build();

        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setmFragment(new AboutFragment())
                .setText("关于")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
