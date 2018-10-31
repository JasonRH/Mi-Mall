package com.example.rh.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.R;

/**
 * @author RH
 * @date 2018/10/30
 */
public class ContentFragment extends BaseAppFragment {
    private static final String CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;

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

    }
}
