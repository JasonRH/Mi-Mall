package com.example.rh.ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.ui.recycler.MultipleItemEntity;
import com.example.rh.core.utils.storage.MyPreference;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author RH
 * @date 2018/11/15
 */
public class SearchFragment extends BaseAppFragment {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit = null;

    @OnClick(R2.id.tv_top_search)
    void onCliclSearch() {
        RetrofitClient.builder()
                .url("search.php?key=")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String searchItemText = mSearchEdit.getText().toString();
                        saveItem(searchItemText);
                        mSearchEdit.setText("");
                        //展示一些东西
                        //弹出一段话
                    }
                })
                .build()
                .get();

        //final String searchItemText = mSearchEdit.getText().toString();
        //saveItem(searchItemText);
    }

    @OnClick(R2.id.icon_top_search_back)
    void onClickBack() {
        getSupportDelegate().pop();
    }

    /**
     * 存储数据
     */
    @SuppressWarnings("unchecked")
    private void saveItem(String item) {
        if (!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)) {
            List<String> history;
            final String historyStr = MyPreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)) {
                history = new ArrayList<>();
            } else {
                //json数据转换成ArrayList
                history = JSON.parseObject(historyStr, ArrayList.class);
            }
            history.add(item);
            final String json = JSON.toJSONString(history);

            MyPreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        final SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);
        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20, 20)
                        .color(Color.GRAY)
                        .build();
            }
        });

        mRecyclerView.addItemDecoration(itemDecoration);
    }
}
