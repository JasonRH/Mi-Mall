package com.example.rh.ec.main.personal.settings;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.main.personal.list.ListBean;

/**
 * @author RH
 * @date 2018/11/10
 */
public class SettingsClickListener extends SimpleClickListener {
    private final BaseAppFragment DELEGATE;

    public SettingsClickListener(BaseAppFragment delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                //这是消息推送的开关
                //不需要打开新的Fragment
                break;
            case 2:
                DELEGATE.getSupportDelegate().start(bean.getmFragment());
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
