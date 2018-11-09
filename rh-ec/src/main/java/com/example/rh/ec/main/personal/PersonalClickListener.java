package com.example.rh.ec.main.personal;


import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.main.personal.list.ListBean;

/**
 * @author RH
 * @date 2018/11/8
 */
public class PersonalClickListener extends SimpleClickListener {
    private final BaseAppFragment fragment;

    public PersonalClickListener(PersonalFragment personalFragment) {
        this.fragment = personalFragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                //为了不在内部跳转，使用getMyParentFragment
                fragment.getMyParentFragment().getSupportDelegate().start(bean.getmFragment());
                break;
            case 2:
                if (bean.getmFragment() != null) {
                    fragment.getMyParentFragment().getSupportDelegate().start(bean.getmFragment());
                } else {
                    Toast.makeText(fragment.getContext(), "功能开发中，敬请关注！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
