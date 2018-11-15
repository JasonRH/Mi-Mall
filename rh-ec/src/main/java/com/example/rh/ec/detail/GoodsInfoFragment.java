package com.example.rh.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/11/15
 */
public class GoodsInfoFragment extends BaseAppFragment {

    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView mGoodsInfoTitle = null;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mGoodsInfoDesc = null;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mGoodsInfoPrice = null;

    private static final String GOODS_DATA = "ARG_GOODS_DATA";
    private JSONObject mData = null;

    public static GoodsInfoFragment create(String goodsInfo) {
        final Bundle args = new Bundle();
        args.putString(GOODS_DATA, goodsInfo);
        final GoodsInfoFragment fragment = new GoodsInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        final String goodsData = bundle.getString(GOODS_DATA);
        mData = JSON.parseObject(goodsData);
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_goods_info;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        final String name = mData.getString("name");
        final String desc = mData.getString("description");
        final double price = mData.getDouble("price");
        mGoodsInfoTitle.setText(name);
        mGoodsInfoDesc.setText(desc);
        mGoodsInfoPrice.setText(String.valueOf(price));
    }
}
