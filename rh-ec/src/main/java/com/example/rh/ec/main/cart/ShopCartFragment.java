package com.example.rh.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.ui.recycler.MultipleItemEntity;
import com.example.rh.core.utils.log.MyLogger;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ec.pay.FastPay;
import com.example.rh.ec.pay.IAlPayResultListener;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author RH
 * @date 2018/10/24
 */
public class ShopCartFragment extends BottomItemFragment implements ISuccess, ICartItemListener ,IAlPayResultListener {

    private ShopCartAdapter mAdapter = null;

    @BindView(R2.id.fragment_cart_recycler)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.fragment_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.fragment_cart_stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.fragment_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    @OnClick(R2.id.fragment_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        //未选择
        if (tag == 0) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            //设置已选择
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态,不刷新整个页面
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.fragment_cart_remove)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        if (data.size() == 0) {
            return;
        }
        if (deleteEntities.size() < 1) {
            Toast.makeText(getContext(), "请选择要删除的商品", Toast.LENGTH_SHORT).show();
            return;
        }
        for (MultipleItemEntity entity : deleteEntities) {
            data.remove(entity);
        }
        //更新数据
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.fragment_cart_clear)
    void onClickClear() {
        int size = mAdapter.getData().size();
        if (size > 0) {
            mAdapter.getData().clear();
            mAdapter.notifyDataSetChanged();
            checkItemCount();
        }
    }

    @OnClick(R2.id.fragment_cart_pay)
    void onClickPay() {
        createOrder();
    }


    @Override
    protected Object setLayout() {
        return R.layout.fragment_shop_cart;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RetrofitClient.builder()
                .loader(getContext())
                .url("json/mall/shop_cart_data.json")
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        MyLogger.d(response);
        final ArrayList<MultipleItemEntity> datas =
                new ShopCartDataConverter()
                        .setJsonData(response)
                        .convert();

        mAdapter = new ShopCartAdapter(datas);
        //价格回调接口
        mAdapter.setCartItemListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        //商品总价格
        mTvTotalPrice.setText(String.valueOf(mAdapter.getTotalPrice()));
    }

    @SuppressLint("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy =
                    (AppCompatTextView) stubView.findViewById(R.id.stub_to_buy_tv);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        //更新商品总价
        mAdapter.initToTalPrice();
        mTvTotalPrice.setText(String.valueOf(mAdapter.getTotalPrice()));
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        //价格回调
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }

    /**
     * 创建订单，注意，和支付是没有关系的
     */
    private void createOrder() {
        final String orderUrl = "create_order.json";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userId", "你的id");
        orderParams.put("amount", 0.01);
        orderParams.put("comment", "测试支付");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 0);
        orderParams.put("isanonymous", true);
        orderParams.put("followeduser", 0);

        RetrofitClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        final int orderId = JSON.parseObject(response).getInteger("result");
                        FastPay.create(ShopCartFragment.this)
                                .setPayResultListener(ShopCartFragment.this)
                                .setOrderId(orderId)
                                .beginPayDialog();

                    }
                })
                .build()
                .post();

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
