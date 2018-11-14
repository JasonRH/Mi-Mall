package com.example.rh.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.utils.callback.CallbackManager;
import com.example.rh.core.utils.callback.CallbackType;
import com.example.rh.core.utils.callback.IGlobalCallback;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;
import com.example.rh.ui.AutoPhotoLayout;
import com.example.rh.ui.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author RH
 * @date 2018/11/12
 */
public class OrderCommentFragment extends BaseAppFragment {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "评分： " + mStarLayout.getStarCount(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_order_comment;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });
    }
}
