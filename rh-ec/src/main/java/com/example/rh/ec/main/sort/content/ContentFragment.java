package com.example.rh.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/10/30
 */
public class ContentFragment extends BaseAppFragment {
    private static final String CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<MySectionEntity> mData = null;

    @BindView(R2.id.fragment_list_content_recycler)
    RecyclerView mRecyclerView = null;

    public static ContentFragment newInstance(int contentId) {
        Bundle bundle = new Bundle();
        bundle.putInt(CONTENT_ID, contentId);
        final ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle != null) {
            mContentId = bundle.getInt(CONTENT_ID);
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_list_content;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        RetrofitClient.builder()
                //.url("sort_content.json?contentId=" + mContentId)
                .url("sort_content_"+mContentId+".json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter = new SectionAdapter(
                                R.layout.item_section_content,
                                R.layout.item_section_header,
                                mData);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .post();
    }

}
