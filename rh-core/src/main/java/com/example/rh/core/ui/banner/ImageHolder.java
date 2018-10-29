package com.example.rh.core.ui.banner;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.rh_core.R;

/**
 * @author RH
 * @date 2018/10/26
 */
public class ImageHolder extends Holder<String> {

    /**
     * 控件不能赋值空
     */
    private AppCompatImageView mImageView;

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public ImageHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = (AppCompatImageView) itemView.findViewById(R.id.banner_image);

    }

    @Override
    public void updateUI(String data) {
        Glide.with(itemView.getContext())
                .load(data)
                .apply(BANNER_OPTIONS)
                .into(mImageView);
    }
}
