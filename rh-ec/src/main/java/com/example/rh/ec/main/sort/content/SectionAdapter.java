package com.example.rh.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.rh.ec.R;

import java.util.List;

/**
 * @author RH
 * @date 2018/10/31
 */
public class SectionAdapter extends BaseSectionQuickAdapter<MySectionEntity, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List<MySectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySectionEntity item) {
        helper.setText(R.id.item_section_content_header, item.header);
        helper.setVisible(R.id.item_section_content_more, item.getIsMore());
        helper.addOnClickListener(R.id.item_section_content_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySectionEntity item) {
        //item.t返回泛型即SectionContentItemEntity类型
        final String thumb = item.t.getGoodsThumb();
        final String name = item.t.getGoodsName();
        final int goodsId = item.t.getGoodsId();
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.item_section_content_tv, name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.item_section_content_iv);
        Glide.with(mContext)
                .load(thumb)
                .into(goodsImageView);
    }
}
